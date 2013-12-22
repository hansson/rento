package com.hansson.rento.dao;

import java.util.List;

import org.hibernate.MockSessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hansson.rento.entities.AuditEvent;
import com.hansson.rento.entities.DailyAuditEvent;
import com.hansson.rento.entities.MonthlyAuditEvent;
import com.hansson.rento.entities.WeeklyAuditEvent;

public class MonthlyAuditEventDAOBeanTest {
	
	private MonthlyAuditEventDAOBean mMonthlyAuditEventDAO;
	
	@Before
	public void setup() {
		mMonthlyAuditEventDAO = new MonthlyAuditEventDAOBean();
		mMonthlyAuditEventDAO.setSessionFactory(new MockSessionFactory(MockSessionFactory.DAILY_AUDIT_EVENT_DAO));
	}

	@Test
	public void testCreate() {
		MonthlyAuditEvent auditEvent = new MonthlyAuditEvent("EVENT","EXTRA");
		MonthlyAuditEvent create = mMonthlyAuditEventDAO.create(auditEvent);
		Assert.assertEquals("EVENT", create.getEvent());
		Assert.assertEquals("EXTRA", create.getExtra());
	}

	@Test
	public void testDelete() {
		MonthlyAuditEvent auditEvent = new MonthlyAuditEvent("EVENT","EXTRA");
		mMonthlyAuditEventDAO.delete(auditEvent);
	}

	@Test
	public void testFindAll() {
		List<MonthlyAuditEvent> findAll = mMonthlyAuditEventDAO.findAll();
		Assert.assertEquals(0, findAll.size());
	}

}
