package com.hansson.rento.dao;

import javax.sql.DataSource;

import com.hansson.rento.entities.Apartment;

public interface ApartmentDAO {

	void setDatasource(DataSource datasource);

	Apartment create(Apartment apartment);

	Apartment delete(Apartment apartment);

	Apartment update(Apartment apartment);

	Apartment find(Apartment apartment);
	
	void createTable();

}
