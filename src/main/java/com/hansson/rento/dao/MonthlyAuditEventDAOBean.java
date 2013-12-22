package com.hansson.rento.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.hansson.rento.entities.MonthlyAuditEvent;

public class MonthlyAuditEventDAOBean implements MonthlyAuditEventDAO {

	private SessionFactory mSessionFactory;

	@Override
	@Transactional
	public MonthlyAuditEvent create(MonthlyAuditEvent auditEvent) {
		Session session = mSessionFactory.getCurrentSession();
		return (MonthlyAuditEvent) session.merge(auditEvent);
	}

	@Override
	@Transactional
	public void delete(MonthlyAuditEvent auditEvent) {
		Session session = mSessionFactory.getCurrentSession();
		session.delete(auditEvent);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<MonthlyAuditEvent> findAll() {
		Session session = mSessionFactory.getCurrentSession();
		Query query = session.createQuery("from MonthlyAuditEvent a ORDER BY mDate DESC");
		return (List<MonthlyAuditEvent>) query.list();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.mSessionFactory = sessionFactory;

	}
}
