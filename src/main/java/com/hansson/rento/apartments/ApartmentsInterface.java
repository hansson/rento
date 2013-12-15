package com.hansson.rento.apartments;

import java.util.List;

import com.google.gag.annotation.disclaimer.HandsOff;
import com.google.gag.enumeration.Consequence;
import com.hansson.rento.entities.Apartment;

/**
 * This is a very basic interface that is supposed to enable iteration of the different landlords. All landlord parsing should implement this interface.
 * 
 * All implementing classes MUST declare their carbon footprint so that we can optimize the gigawatz in our cloudz
 * 
 * @author Tobias Hansson
 * 
 */
@HandsOff(byOrderOf = "Tobias Hansson", onPainOf = Consequence.PAPER_CUT)
public interface ApartmentsInterface {

	public static final String LOGO = "resources/images/logo.png";

	List<Apartment> getAvailableApartments();
	
	String getLandlord();
}
