package com.hansson.rento;

import java.util.List;

import org.jsoup.nodes.Document;

import com.hansson.rento.apartments.ApartmentUtils;
import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.apartments.Method;
import com.hansson.rento.entities.Apartment;

public class KonradssonsFastigheter extends ApartmentUtils implements ApartmentsInterface {

	private final static String LANDLORD = "Konradssons Fastigheter";
	private final static String BASE_URL = "http://www.blocket.se/peter-konradssons-fastigheter";
	
	@Override
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentList = null;
		// Get html for first page
		Document doc = connect(BASE_URL);
		if (doc != null) {
			apartmentList = getApartmentsMultiPage(doc, BASE_URL, LANDLORD, Method.BLOCKET);
		}
		return apartmentList;
	}

	@Override
	public String getLandlord() {
		return LANDLORD;
	}

}
