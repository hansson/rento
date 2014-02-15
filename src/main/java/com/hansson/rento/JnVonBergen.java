package com.hansson.rento;

import java.util.List;

import org.jsoup.nodes.Document;

import com.hansson.rento.apartments.ApartmentUtils;
import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.apartments.Method;
import com.hansson.rento.entities.Apartment;

public class JnVonBergen extends ApartmentUtils implements ApartmentsInterface {

	private final static String LANDLORD = "JN von Bergen &amp; Son";
	private final static String BASE_URL = "http://www.jnvonbergen.se/von_Bergen300Sida6.htm";
	
	@Override
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentList = null;
		return apartmentList;
	}

	@Override
	public String getLandlord() {
		return LANDLORD;
	}

}
