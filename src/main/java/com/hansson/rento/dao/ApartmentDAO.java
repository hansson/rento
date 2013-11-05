package com.hansson.rento.dao;

import com.hansson.rento.entities.Apartment;

public interface ApartmentDAO {

	Apartment create(Apartment apartment);

	Apartment delete(Apartment apartment);

	Apartment update(Apartment apartment);

	Apartment find(int id);
	
}
