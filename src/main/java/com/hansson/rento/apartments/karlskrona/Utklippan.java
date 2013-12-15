package com.hansson.rento.apartments.karlskrona;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.entities.Apartment;

public class Utklippan implements ApartmentsInterface {

	private static final String LANDLORD = "Utklippan";
	private static final String CITY = "Karlskrona";
	private static final String BASE_URL = "http://www.utklippanfastigheter.se";
	private Logger mLog = LoggerFactory.getLogger("rento");

	@Override
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
					apartment.setAddress(element.getElementsByTag("td").get(1).text());
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

	@Override
	public String getLandlord() {
		return LANDLORD;
	}
}
