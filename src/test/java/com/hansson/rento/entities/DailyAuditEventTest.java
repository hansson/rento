package com.hansson.rento.entities;


import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DailyAuditEventTest {
	
	private DailyAuditEvent mDailyAuditEvent;
	
	@Before
	public void setup() {
		mDailyAuditEvent = new DailyAuditEvent();
	}

	@Test
	public void testDailyAuditEvent() {
		mDailyAuditEvent = new DailyAuditEvent("EVENT", "EXTRA");
		Assert.assertEquals("EVENT", mDailyAuditEvent.getEvent());
		Assert.assertEquals("EXTRA", mDailyAuditEvent.getExtra());
	}

	@Test
	public void testGetId() {
		mDailyAuditEvent.setId(1);
		Assert.assertEquals(new Integer(1),mDailyAuditEvent.getId());
	}

	@Test
	public void testGetEvent() {
		mDailyAuditEvent.setEvent("EVENT");
		Assert.assertEquals("EVENT", mDailyAuditEvent.getEvent());
	}

	@Test
	public void testGetAdded() {
		Date date = new Date();
		mDailyAuditEvent.setDate(date);
		Assert.assertEquals(date, mDailyAuditEvent.getDate());
	}

	@Test
	public void testAdd() {
		Assert.assertEquals(new Integer(0), mDailyAuditEvent.getCount());
		mDailyAuditEvent.add();
		Assert.assertEquals(new Integer(1), mDailyAuditEvent.getCount());
	}

	@Test
	public void testGetExtra() {
		mDailyAuditEvent.setExtra("EXTRA");
		Assert.assertEquals("EXTRA", mDailyAuditEvent.getExtra());
	}
}
