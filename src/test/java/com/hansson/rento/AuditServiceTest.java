package com.hansson.rento;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hansson.rento.dao.MockAuditEventDAOBean;
import com.hansson.rento.dao.MockDailyAuditEventDAOBean;
import com.hansson.rento.dao.MockMonthlyAuditEventDAOBean;

public class AuditServiceTest {
	
	private AuditService mAuditService;
	private MockAuditEventDAOBean mAuditEventDAOBean;
	private MockDailyAuditEventDAOBean mDailyAuditEventDAOBean;
	private MockMonthlyAuditEventDAOBean mMonthlyAuditEventDAOBean;

	@Before
	public void setUp() throws Exception {
		mAuditService = new AuditService();
		mAuditEventDAOBean = new MockAuditEventDAOBean();
		mDailyAuditEventDAOBean = new MockDailyAuditEventDAOBean();
		mMonthlyAuditEventDAOBean = new MockMonthlyAuditEventDAOBean();
		mAuditService.setAuditEventDAO(mAuditEventDAOBean);
		mAuditService.setDailyAuditEventDAO(mDailyAuditEventDAOBean);
		mAuditService.setMonthlyAuditEventDAO(mMonthlyAuditEventDAOBean);
	}

	@Test
	public void testPutAuditEvent() {
		mAuditService.putAuditEvent("EVENT", "EXTRA");
		Assert.assertEquals(1, mAuditEventDAOBean.findAll().size());
	}

	@Test
	public void testDailyAuditAggregation() {
		mAuditService.putAuditEvent("EVENT", "EXTRA");
		mAuditService.dailyAuditAggregation();
		Assert.assertEquals(1, mDailyAuditEventDAOBean.findAll().size());
	}

	@Test
	public void testMonthlyAuditAggregation() {
		mAuditService.putAuditEvent("EVENT", "EXTRA");
		mAuditService.dailyAuditAggregation();
		mAuditService.monthlyAuditAggregation();
		Assert.assertEquals(1, mMonthlyAuditEventDAOBean.findAll().size());
	}

}
