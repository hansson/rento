package com.hansson.rento.apartments.multiple;

import java.util.LinkedList;
import java.util.List;


import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.entities.Apartment;

/**
 * Krebo has no  apartment listing, hard coding apartments.
 * @author hansson
 *
 */

public class Krebo implements ApartmentsInterface {

	private static final String LANDLORD = "Krebo studentl&auml;genhet";
	
	@Override
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartments = new LinkedList<Apartment>();
		Apartment apartment = new Apartment(LANDLORD);
		apartment.setCity("Karlskrona");
		apartment.setIdentifier("krebo-hardcoded");
		apartment.setLandlord(LANDLORD);
		apartment.setRooms(1.0);
		apartment.setAddress("Minervavägen");
		apartment.setStudent(true);
		apartment.setUrl("http://www.krebo.se/default.asp?page=4");
		apartments.add(apartment);
		
		Apartment apartment2 = new Apartment(LANDLORD);
		apartment2.setCity("Karlstad");
		apartment2.setIdentifier("krebo-hardcoded");
		apartment2.setLandlord(LANDLORD);
		apartment2.setRent(4788);
		apartment2.setRooms(1.0);
		apartment2.setSize(34);
		apartment2.setStudent(true);
		apartment2.setUrl("http://www.krebo.se/default.asp?page=4");
		apartments.add(apartment2);
		
		Apartment apartment3 = new Apartment(LANDLORD);
		apartment3.setCity("Kristianstad");
		apartment3.setIdentifier("krebo-hardcoded");
		apartment3.setLandlord(LANDLORD);
		apartment3.setRent(3898);
		apartment3.setRooms(1.0);
		apartment3.setSize(29);
		apartment3.setAddress("Olastorpsv&auml;gen");
		apartment3.setStudent(true);
		apartment3.setUrl("http://www.krebo.se/default.asp?page=4");
		apartments.add(apartment3);
		
		Apartment apartment4 = new Apartment(LANDLORD);
		apartment4.setCity("Lund");
		apartment4.setIdentifier("krebo-hardcoded");
		apartment4.setLandlord(LANDLORD);
		apartment4.setRent(4611);
		apartment4.setRooms(1.0);
		apartment4.setSize(34);
		apartment4.setAddress("Fiolv&auml;gen 54-68");
		apartment4.setStudent(true);
		apartment4.setUrl("http://www.krebo.se/default.asp?page=4");
		apartments.add(apartment4);
		
		Apartment apartment5 = new Apartment(LANDLORD);
		apartment5.setCity("Trollh&auml;ttan");
		apartment5.setIdentifier("krebo-hardcoded");
		apartment5.setLandlord(LANDLORD);
		apartment5.setRent(4259);
		apartment5.setRooms(1.0);
		apartment5.setSize(34);
		apartment5.setAddress("Karlstorpsv&auml;gen 120-142");
		apartment5.setStudent(true);
		apartment5.setUrl("http://www.krebo.se/default.asp?page=4");
		apartments.add(apartment5);
		
		Apartment apartment6 = new Apartment(LANDLORD);
		apartment6.setCity("V&auml;xj&ouml;");
		apartment6.setIdentifier("krebo-hardcoded");
		apartment6.setLandlord(LANDLORD);
		apartment6.setRent(4155);
		apartment6.setRooms(1.0);
		apartment6.setSize(29);
		apartment6.setAddress("Gamla Teleborgsv&auml;gen 1-11");
		apartment6.setStudent(true);
		apartment6.setUrl("http://www.krebo.se/default.asp?page=4");
		apartments.add(apartment6);
		return apartments ;
	}
	
	@Override
	public String getLandlord() {
		return LANDLORD;
	}
}
