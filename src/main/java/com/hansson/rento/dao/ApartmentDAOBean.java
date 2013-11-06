package com.hansson.rento.dao;

import java.util.List;

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
	public void delete(Apartment apartment) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(apartment);
	}

	@Override
	@Transactional
	public Apartment update(Apartment apartment) {
		Session session = sessionFactory.getCurrentSession();
		return (Apartment) session.merge(apartment);
	}

	@Override
	@Transactional
	public Apartment find(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Apartment a where a.id = " + id);
		return (Apartment) query.uniqueResult();
	}

	@Override
	@Transactional
	public Apartment find(String landlord, String identifier) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Apartment a where a.mIdentifier = '" + identifier + "' and a.mLandlord = '" + landlord +"'");
		return (Apartment) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Apartment> findAll() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Apartment a");
		return (List<Apartment>)query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Apartment> findAll(String landlord) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Apartment a where a.mLandlord = '" + landlord +"'");
		return (List<Apartment>)query.list();
	}


}
