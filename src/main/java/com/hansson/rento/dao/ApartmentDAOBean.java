package com.hansson.rento.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.hansson.rento.entitys.Apartment;

public class ApartmentDAOBean implements ApartmentDAO {

	private JdbcTemplate mDatasource;

	@Override
	public Apartment create(Apartment apartment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Apartment delete(Apartment apartment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Apartment update(Apartment apartment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Apartment find(Apartment apartment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDatasource(DataSource datasource) {
		mDatasource = new JdbcTemplate(datasource);

	}

}
