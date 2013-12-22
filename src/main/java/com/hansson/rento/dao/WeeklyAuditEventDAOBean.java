package com.hansson.rento.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.hansson.rento.entities.WeeklyAuditEvent;

public class WeeklyAuditEventDAOBean implements WeeklyAuditEventDAO {

	private SessionFactory mSessionFactory;

	@Override
	@Transactional
	public WeeklyAuditEvent create(WeeklyAuditEvent auditEvent) {
		Session session = mSessionFactory.getCurrentSession();
		return (WeeklyAuditEvent) session.merge(auditEvent);
	}

	@Override
	@Transactional
	public void delete(WeeklyAuditEvent auditEvent) {
		Session session = mSessionFactory.getCurrentSession();
		session.delete(auditEvent);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<WeeklyAuditEvent> findAll() {
		Session session = mSessionFactory.getCurrentSession();
		Query query = session.createQuery("from WeeklyAuditEvent a ORDER BY mDate DESC");
		return (List<WeeklyAuditEvent>) query.list();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.mSessionFactory = sessionFactory;

	}
}
