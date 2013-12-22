package com.hansson.rento.dao;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.hansson.rento.entities.DailyAuditEvent;

public class MockDailyAuditEventDAOBean implements DailyAuditEventDAO {
	
	private List<DailyAuditEvent> datastore = new LinkedList<DailyAuditEvent>();

	@Override
	public DailyAuditEvent create(DailyAuditEvent auditEvent) {
		datastore.add(auditEvent);
		return auditEvent;
	}

	@Override
	public void delete(DailyAuditEvent auditEvent) {
		datastore.remove(auditEvent);
	}

	@Override
	public List<DailyAuditEvent> findAll() {
		List<DailyAuditEvent> returnList = new LinkedList<DailyAuditEvent>();
		for(DailyAuditEvent event : datastore) {
			returnList.add(event);
		}
		return returnList;
	}

}
