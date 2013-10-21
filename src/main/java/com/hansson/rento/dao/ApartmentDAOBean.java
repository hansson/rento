package com.hansson.rento.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.hansson.rento.entities.Apartment;

public class ApartmentDAOBean implements ApartmentDAO {

	private NamedParameterJdbcTemplate mDatasource;

	@Override
	public Apartment create(Apartment apartment) {
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(apartment);
		mDatasource.update(Apartment.INSERT_STATEMENT, namedParameters);
		return apartment;
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
		mDatasource = new NamedParameterJdbcTemplate(datasource);
	}

	@Override
	public void createTable() {
		mDatasource.getJdbcOperations().execute(Apartment.CREATE_STATEMENT);
	}

}
