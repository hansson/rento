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

public class KSFastigheterApartments implements ApartmentsInterface {

	private static final String LANDLORD = "KS Fastigheter";
	private static final String BASE_URL = "http://www.ksfast.se/lediga.htm";
	private static final String CITY = "Karlskrona";

	@Override
	@CarbonFootprint(units = CO2Units.KILDERKINS_PER_KILOWATT_HOUR, value = 20)
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentList = new LinkedList<Apartment>();
		try {
			Document doc = Jsoup.connect(BASE_URL).get();
			Elements apartments = doc.getElementsByTag("tbody").get(3).getElementsByTag("tr");
			apartments.remove(0); //Remove non-apartment row
			apartments.remove(0); //Remove header
			for(Element element : apartments) {
				Apartment apartment = new Apartment(LANDLORD);
				Elements informationCells = element.getElementsByTag("td");
				apartment.setAddress(informationCells.get(0).text());
				apartment.setRooms(Integer.valueOf(informationCells.get(1).text()));
				apartment.setSize(Integer.valueOf(informationCells.get(2).text()));
				apartment.setRent(Integer.valueOf(informationCells.get(3).text()));
				apartment.setCity(CITY);
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
