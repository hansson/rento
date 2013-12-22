package com.hansson.rento.dao;

import java.util.List;

import org.hibernate.MockSessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hansson.rento.entities.AuditEvent;
import com.hansson.rento.entities.DailyAuditEvent;

public class DailyAuditEventDAOBeanTest {
	
	private DailyAuditEventDAOBean dailyAuditEventDAO;
	
	@Before
	public void setup() {
		dailyAuditEventDAO = new DailyAuditEventDAOBean();
		dailyAuditEventDAO.setSessionFactory(new MockSessionFactory(MockSessionFactory.DAILY_AUDIT_EVENT_DAO));
	}

	@Test
	public void testCreate() {
		DailyAuditEvent auditEvent = new DailyAuditEvent("EVENT","EXTRA");
		DailyAuditEvent create = dailyAuditEventDAO.create(auditEvent);
		Assert.assertEquals("EVENT", create.getEvent());
		Assert.assertEquals("EXTRA", create.getExtra());
	}

	@Test
	public void testDelete() {
		DailyAuditEvent auditEvent = new DailyAuditEvent("EVENT","EXTRA");
		dailyAuditEventDAO.delete(auditEvent);
	}

	@Test
	public void testFindAll() {
		List<DailyAuditEvent> findAll = dailyAuditEventDAO.findAll();
		Assert.assertEquals(0, findAll.size());
	}

}
