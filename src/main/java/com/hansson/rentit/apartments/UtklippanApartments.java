package com.hansson.rentit.apartments;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gag.annotation.disclaimer.CarbonFootprint;
import com.google.gag.enumeration.CO2Units;
import com.hansson.rentit.entitys.Apartment;
import com.hansson.rentit.utils.HtmlUtil;

public class UtklippanApartments implements ApartmentsInterface {

	private static final String LANDLORD = "Svenska Bostadsfonden";
	private static final String CITY = "Karlskrona";
	private static final String BASE_URL = "http://www.utklippanfastigheter.se";
	private static final Logger mLog = LoggerFactory.getLogger("RENTIT");

	@Override
	@CarbonFootprint(units = CO2Units.KILDERKINS_PER_KILOWATT_HOUR, value = 20)
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentList = new LinkedList<Apartment>();
		try {
			Document doc = Jsoup.connect(BASE_URL + "/pages/fastigheter/ledigt-just-nu.php").get();
			Elements apartments = doc.getElementById("wrapper").getElementsByTag("tr");
			apartments.remove(0);
			apartments.remove(0);
			apartments.remove(apartments.size() - 1);
			for (Element element : apartments) {
				try {
					Apartment apartment = new Apartment(LANDLORD);
					apartment.setUrl(BASE_URL + element.getElementsByTag("td").get(0).getElementsByTag("a").attr("href")); 
					apartment.setCity(CITY);
					apartment.setAddress(HtmlUtil.textToHtml(element.getElementsByTag("td").get(1).text()));
					apartment.setRooms(Double.valueOf(element.getElementsByTag("td").get(2).text().replaceAll(",", ".")));
					apartment.setSize(Integer.valueOf(element.getElementsByTag("td").get(3).text().split(" ")[0]));
					apartment.setRent(Integer.valueOf(element.getElementsByTag("td").get(4).text().replaceAll("kr| ", "")));
					apartment.setIdentifier(apartment.getUrl().split("obj_spec\\.php")[1]);
					apartmentList.add(apartment);
				} catch (Exception e) {
					mLog.error(LANDLORD + " error on element #" + apartments.indexOf(element));
					e.printStackTrace();
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return apartmentList;
	}
}
