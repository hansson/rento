package com.hansson.rento.apartments.blekinge.karlskrona;

import java.util.LinkedList;
import java.util.List;

import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.entities.Apartment;

/**
 * PBA Student has no  apartment listing, hard coding apartments.
 * @author hansson
 *
 */

public class PBAStudent implements ApartmentsInterface {

	private static final String LANDLORD = "PBA studentl&auml;genhet";
	
	@Override
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartments = new LinkedList<Apartment>();
		Apartment apartment = new Apartment(LANDLORD);
		apartment.setCity("Karlskrona");
		apartment.setArea("Annebo");
		apartment.setIdentifier("pba-student-hardcoded");
		apartment.setLandlord(LANDLORD);
		apartment.setRent(4400);
		apartment.setRooms(1.0);
		apartment.setSize(37);
		apartment.setAddress("Gamla Infartsv\u00e4gen 3 A");
		apartment.setStudent(true);
		apartment.setUrl("http://www.pba.se/page/16/studentbostader.aspx");
		apartments.add(apartment); 
		return apartments ;
	}
	
	@Override
	public String getLandlord() {
		return LANDLORD;
	}
}
