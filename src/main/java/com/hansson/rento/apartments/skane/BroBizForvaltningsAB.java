package com.hansson.rento.apartments.skane;

import java.util.List;

import org.jsoup.nodes.Document;

import com.hansson.rento.apartments.ApartmentUtils;
import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.apartments.Method;
import com.hansson.rento.entities.Apartment;

public class BroBizForvaltningsAB extends ApartmentUtils implements ApartmentsInterface {
	
	private final static String LANDLORD = "BroBiz F&ouml;rvaltnings AB";
	private final static String BASE_URL = "http://www.brobiz.se/ledigalgh.htm";
	
	@Override
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentList = null;	
		Document doc = connect(BASE_URL);
		if (doc != null) {
			apartmentList = getApartmentsSinglePage(doc, BASE_URL, LANDLORD, Method.TABLE);
		}
		return apartmentList;
	}

	@Override
	public String getLandlord() {
		return LANDLORD;
	}

}