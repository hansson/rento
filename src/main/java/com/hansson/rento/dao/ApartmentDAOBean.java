package com.hansson.rento.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hansson.rento.entities.Apartment;

@Repository
public class ApartmentDAOBean implements ApartmentDAO {
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Apartment create(Apartment apartment) {
		Session session = sessionFactory.getCurrentSession();
		return (Apartment) session.merge(apartment);
	}

	@Override
	@Transactional
	public Apartment delete(Apartment apartment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Apartment update(Apartment apartment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Apartment find(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Apartments a where a.id = " + id);
		return (Apartment) query.uniqueResult();
	}

}
