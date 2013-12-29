package com.hansson.rento.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.hansson.rento.entities.AuditEvent;

public class AuditEventDAOBean implements AuditEventDAO {

	private SessionFactory mSessionFactory;

	@Override
	@Transactional
	public AuditEvent create(AuditEvent auditEvent) {
		Session session = mSessionFactory.getCurrentSession();
		return (AuditEvent) session.merge(auditEvent);
	}

	@Override
	@Transactional
	public void delete(AuditEvent auditEvent) {
		Session session = mSessionFactory.getCurrentSession();
		session.delete(auditEvent);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<AuditEvent> findAll() {
		Session session = mSessionFactory.getCurrentSession();
		Query query = session.createQuery("from AuditEvent a ORDER BY mDate DESC");
		return (List<AuditEvent>) query.list();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.mSessionFactory = sessionFactory;

	}

}
