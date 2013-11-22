package com.hansson.rento.dao;

import java.util.List;

import com.hansson.rento.entities.Apartment;

public interface ApartmentDAO {

	Apartment create(Apartment apartment);

	void delete(Apartment apartment);

	Apartment update(Apartment apartment);

	Apartment find(int id);
	
	Apartment find(String landlord, String identifier);
	
	List<Apartment> findAll();

	List<Apartment> findAllByCity(String city);

	List<Apartment> findAllByLandlord(String landlord);
	
	List<String> findAllCities();

	
	
}
