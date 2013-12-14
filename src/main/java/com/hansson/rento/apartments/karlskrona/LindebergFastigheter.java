package com.hansson.rento.apartments.karlskrona;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.entities.Apartment;

public class LindebergFastigheter implements ApartmentsInterface {

	private final static String KARLSKRONA = "Karlskrona";
	private final static String LANDLORD = "Lindeberg Fastigheter";
	private final static String BASE_URL = "http://www.lindebergfastigheter.se/";

	private Logger mLog = LoggerFactory.getLogger("rento");

	@Override
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentList = new LinkedList<Apartment>();
		try {
			Document doc = Jsoup.connect(BASE_URL + "ajax.aspx?content=3").get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return apartmentList;
	}

	@Override
	public String getLandlord() {
		return LANDLORD;
	}
}
