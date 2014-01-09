package com.hansson.rento.apartments.multiple;

import java.util.List;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hansson.rento.apartments.ApartmentUtils;
import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.entities.Apartment;

public class SolvedalsForvaltning extends ApartmentUtils implements ApartmentsInterface {

	private final static String LANDLORD = "S&ouml;lvedals F&ouml;rvaltnings AB";
	private final static String BASE_URL = "http://www.solvedals.se/";
	
	private static final Logger mLog = LoggerFactory.getLogger("rento");

	@Override
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentList = null;
		// Get html for first page
		Document doc = connect(BASE_URL + "?page_id=11");
		if (doc != null) {
			apartmentList = getApartmentsMultiPage(doc, BASE_URL, LANDLORD);
		}
		return apartmentList;
	}

	@Override
	public String getLandlord() {
		return LANDLORD;
	}
}
