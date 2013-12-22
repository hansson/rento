package com.hansson.rento.dao;

import java.util.LinkedList;
import java.util.List;

import com.hansson.rento.entities.WeeklyAuditEvent;


public class MockWeeklyAuditEventDAOBean implements WeeklyAuditEventDAO {
	
	private List<WeeklyAuditEvent> datastore = new LinkedList<WeeklyAuditEvent>();

	@Override
	public WeeklyAuditEvent create(WeeklyAuditEvent auditEvent) {
		datastore.add(auditEvent);
		return auditEvent;
	}

	@Override
	public void delete(WeeklyAuditEvent auditEvent) {
		datastore.remove(auditEvent);
	}

	@Override
	public List<WeeklyAuditEvent> findAll() {
		List<WeeklyAuditEvent> returnList = new LinkedList<WeeklyAuditEvent>();
		for(WeeklyAuditEvent event : datastore) {
			returnList.add(event);
		}
		return returnList;
	}

}
