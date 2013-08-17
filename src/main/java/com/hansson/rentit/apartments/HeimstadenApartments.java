package com.hansson.rentit.apartments;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.gag.annotation.disclaimer.CarbonFootprint;
import com.google.gag.enumeration.CO2Units;
import com.hansson.rentit.entitys.Apartment;

public class HeimstadenApartments implements ApartmentsInterface {

	private final static String LANDLORD = "Heimstaden";
	private final static String BASE_URL = "http://www.heimstaden.com";

	@Override
	@CarbonFootprint(units = CO2Units.FIRKINS_PER_FORTNIGHT, value = 167.5)
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentLIst = new LinkedList<Apartment>();
		try {
			// Get html for first page
			Document doc = Jsoup.connect(BASE_URL + "/For_sokande/Lediga_bostader").get();
			Elements elementsByClass = doc.getElementsByClass("appartmentSimpleItem");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return apartmentLIst;
	}
}
