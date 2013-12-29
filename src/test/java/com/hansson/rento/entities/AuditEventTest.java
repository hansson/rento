package com.hansson.rento.entities;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AuditEventTest {

	private AuditEvent mAuditEvent;
	
	@Before
	public void setup() {
		mAuditEvent = new AuditEvent();
	}
	
	@Test
	public void testConstructor() {
		mAuditEvent = new AuditEvent("EVENT", "EXTRA");
		Assert.assertEquals("EVENT", mAuditEvent.getEvent());
		Assert.assertEquals("EXTRA", mAuditEvent.getExtra());
	}
	
	@Test
	public void testGetId() {
		mAuditEvent.setId(1);
		Assert.assertEquals(new Integer(1), mAuditEvent.getId());
	}

	@Test
	public void testGetEvent() {
		mAuditEvent.setEvent("EVENT");
		Assert.assertEquals("EVENT", mAuditEvent.getEvent());
	}

	@Test
	public void testGetDate() {
		Date date = new Date();
		mAuditEvent.setDate(date);
		Assert.assertEquals(date, mAuditEvent.getDate());
	}

	@Test
	public void testGetExtra() {
		mAuditEvent.setExtra("EXTRA");
		Assert.assertEquals("EXTRA", mAuditEvent.getExtra());
	}

}
