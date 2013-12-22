package com.hansson.rento.dao;

import java.util.List;

import org.hibernate.MockSessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hansson.rento.entities.AuditEvent;
import com.hansson.rento.entities.DailyAuditEvent;
import com.hansson.rento.entities.WeeklyAuditEvent;

public class WeeklyAuditEventDAOBeanTest {
	
	private WeeklyAuditEventDAOBean mWeeklyAuditEventDAO;
	
	@Before
	public void setup() {
		mWeeklyAuditEventDAO = new WeeklyAuditEventDAOBean();
		mWeeklyAuditEventDAO.setSessionFactory(new MockSessionFactory(MockSessionFactory.DAILY_AUDIT_EVENT_DAO));
	}

	@Test
	public void testCreate() {
		WeeklyAuditEvent auditEvent = new WeeklyAuditEvent("EVENT","EXTRA");
		WeeklyAuditEvent create = mWeeklyAuditEventDAO.create(auditEvent);
		Assert.assertEquals("EVENT", create.getEvent());
		Assert.assertEquals("EXTRA", create.getExtra());
	}

	@Test
	public void testDelete() {
		WeeklyAuditEvent auditEvent = new WeeklyAuditEvent("EVENT","EXTRA");
		mWeeklyAuditEventDAO.delete(auditEvent);
	}

	@Test
	public void testFindAll() {
		List<WeeklyAuditEvent> findAll = mWeeklyAuditEventDAO.findAll();
		Assert.assertEquals(0, findAll.size());
	}

}
