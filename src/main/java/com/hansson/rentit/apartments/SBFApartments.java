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

public class SBFApartments implements ApartmentsInterface {

	private static final String LANDLORD = "Svenska Bostadsfonden";
	private static final String BASE_URL = "http://www.sbfbostad.se";
	private static final Logger mLog = LoggerFactory.getLogger("RENTIT");

	@Override
	@CarbonFootprint(units = CO2Units.KILDERKINS_PER_KILOWATT_HOUR, value = 20)
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentList = new LinkedList<Apartment>();
		try {
			Document doc = Jsoup.connect(BASE_URL + "/pages/Lediga_lagenheter.aspx").get();
			Elements apartments = doc.getElementsByClass("listRow");
			apartments.addAll(doc.getElementsByClass("listRowGray"));
			for (Element element : apartments) {
				try {
					Apartment apartment = new Apartment(LANDLORD);
					apartment.setCity(HtmlUtil.textToHtml(element.getElementsByClass("colOrt").text()));
					apartment.setAddress(HtmlUtil.textToHtml(element.getElementsByClass("colAdress").text()));
					apartment.setRooms(Double.valueOf(element.getElementsByClass("colRum").text().replaceAll(",", ".")));
					apartment.setRent(Integer.valueOf(element.getElementsByClass("colHyra").text().replaceAll("kr| ", "")));
					apartment.setSize(Integer.valueOf(element.getElementsByClass("colArea").text()));
					apartment.setUrl(BASE_URL + element.getElementsByTag("a").attr("href"));
					apartment.setIdentifier(element.getElementsByTag("a").attr("href").split("\\?")[1].split("=")[1]);
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
