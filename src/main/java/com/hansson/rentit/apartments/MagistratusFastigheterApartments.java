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

import com.google.gag.annotation.disclaimer.CarbonFootprint;
import com.google.gag.enumeration.CO2Units;
import com.hansson.rentit.entitys.Apartment;
import com.hansson.rentit.utils.HtmlUtil;

public class MagistratusFastigheterApartments implements ApartmentsInterface {

	private static final String LANDLORD = "Magistratus Fastigheter";
	private static final String BASE_URL = "http://www.magistratus.se/index.php?go=hyresledigt";
	private static final String CITY = "Karlskrona";

	@Override
	@CarbonFootprint(units = CO2Units.KILDERKINS_PER_KILOWATT_HOUR, value = 20)
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentList = new LinkedList<Apartment>();
		try {
			Document doc = Jsoup.connect(BASE_URL).get();
			Elements apartments = doc.getElementsByClass("apartmenttable");
			for(Element element : apartments) {
				Apartment apartment = new Apartment(LANDLORD);
				Elements informationCells = element.getElementsByTag("tr");
				apartment.setRooms(Integer.valueOf(informationCells.get(2).getAllElements().get(2).text().replaceAll("\\D", "")));
				apartment.setSize(Integer.valueOf(informationCells.get(2).getAllElements().get(4).text().replaceAll("\\D", "")));
				apartment.setRent(Integer.valueOf(informationCells.get(2).getAllElements().get(6).text().replaceAll("\\D", "")));
				String[] addressInformation = informationCells.get(3).getAllElements().get(2).text().replaceAll("[\\d]{3} [\\d]{2} ", "").split(" ");
				apartment.setCity(CITY);
				apartment.setArea(HtmlUtil.textToHtml(addressInformation[addressInformation.length - 1]));
				String address = "";
				for(int i = 0 ; i < addressInformation.length - 1 ; i++) {
					address += addressInformation[i];
				}
				apartment.setAddress(HtmlUtil.textToHtml(address));
				apartment.setIdentifier(apartment.getAddress() + apartment.getCity() + apartment.getRent() + apartment.getSize() + apartment.getRooms());
				apartment.setUrl(BASE_URL);
				apartmentList.add(apartment);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return apartmentList;
	}
}
