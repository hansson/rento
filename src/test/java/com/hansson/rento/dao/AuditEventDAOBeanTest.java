package com.hansson.rento.dao;

import java.util.List;

import org.hibernate.MockSessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hansson.rento.entities.AuditEvent;

public class AuditEventDAOBeanTest {
	
	private AuditEventDAOBean mAuditEventDAO;
	
	@Before
	public void setup() {
		mAuditEventDAO = new AuditEventDAOBean();
		mAuditEventDAO.setSessionFactory(new MockSessionFactory(MockSessionFactory.AUDIT_EVENT_DAO));
	}

	@Test
	public void testCreate() {
		AuditEvent auditEvent = new AuditEvent("EVENT","EXTRA");
		AuditEvent create = mAuditEventDAO.create(auditEvent);
		Assert.assertEquals("EVENT", create.getEvent());
		Assert.assertEquals("EXTRA", create.getExtra());
	}

	@Test
	public void testDelete() {
		AuditEvent auditEvent = new AuditEvent("EVENT","EXTRA");
		mAuditEventDAO.delete(auditEvent);
	}

	@Test
	public void testFindAll() {
		List<AuditEvent> findAll = mAuditEventDAO.findAll();
		Assert.assertEquals(0, findAll.size());
	}

}
