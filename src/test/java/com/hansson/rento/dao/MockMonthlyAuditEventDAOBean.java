package com.hansson.rento.dao;

import java.util.LinkedList;
import java.util.List;

import com.hansson.rento.entities.MonthlyAuditEvent;

public class MockMonthlyAuditEventDAOBean implements MonthlyAuditEventDAO {
	
	private List<MonthlyAuditEvent> datastore = new LinkedList<MonthlyAuditEvent>();

	@Override
	public MonthlyAuditEvent create(MonthlyAuditEvent auditEvent) {
		datastore.add(auditEvent);
		return auditEvent;
	}

	@Override
	public void delete(MonthlyAuditEvent auditEvent) {
		datastore.remove(auditEvent);
	}

	@Override
	public List<MonthlyAuditEvent> findAll() {
		List<MonthlyAuditEvent> returnList = new LinkedList<MonthlyAuditEvent>();
		for(MonthlyAuditEvent event : datastore) {
			returnList.add(event);
		}
		return returnList;
	}

}
