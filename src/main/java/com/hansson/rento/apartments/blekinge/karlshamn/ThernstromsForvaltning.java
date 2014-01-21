package com.hansson.rento.apartments.blekinge.karlshamn;

import java.util.List;

import org.jsoup.nodes.Document;

import com.hansson.rento.apartments.ApartmentUtils;
import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.apartments.Method;
import com.hansson.rento.entities.Apartment;

public class ThernstromsForvaltning extends ApartmentUtils implements ApartmentsInterface {

	private final static String LANDLORD = "Thernstr&ouml;ms F&ouml;rvaltnings AB";
	private final static String BASE_URL = "http://www.thernstroms.se/index.php?ID=83";
	
	@Override
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentList = null;
		Document doc = connect(BASE_URL);
		if (doc != null) {
			apartmentList = getApartmentsSinglePage(doc, BASE_URL, LANDLORD, Method.TABLE_SINGLE_COLUMN);
		}
		
		//The city can not be parsed from descrption, set it to the remaining apartments
		for(Apartment apartment : apartmentList) {
			apartment.setCity("Karlshamn");
		}
		return apartmentList;
	}

	@Override
	public String getLandlord() {
		return LANDLORD;
	}
}
