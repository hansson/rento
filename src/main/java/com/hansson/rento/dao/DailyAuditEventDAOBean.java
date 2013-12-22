package com.hansson.rento.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.hansson.rento.entities.AuditEvent;
import com.hansson.rento.entities.DailyAuditEvent;

public class DailyAuditEventDAOBean implements DailyAuditEventDAO {

	private SessionFactory mSessionFactory;

	@Override
	@Transactional
	public DailyAuditEvent create(DailyAuditEvent auditEvent) {
		Session session = mSessionFactory.getCurrentSession();
		return (DailyAuditEvent) session.merge(auditEvent);
	}

	@Override
	@Transactional
	public void delete(DailyAuditEvent auditEvent) {
		Session session = mSessionFactory.getCurrentSession();
		session.delete(auditEvent);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<DailyAuditEvent> findAll() {
		Session session = mSessionFactory.getCurrentSession();
		Query query = session.createQuery("from DailyAuditEvent a ORDER BY mDate DESC");
		return (List<DailyAuditEvent>) query.list();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.mSessionFactory = sessionFactory;

	}

}
