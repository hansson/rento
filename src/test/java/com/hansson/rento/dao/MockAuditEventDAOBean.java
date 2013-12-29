package com.hansson.rento.dao;

import java.util.LinkedList;
import java.util.List;

import com.hansson.rento.entities.AuditEvent;

public class MockAuditEventDAOBean implements AuditEventDAO {
	
	private List<AuditEvent> datastore = new LinkedList<AuditEvent>(); 

	@Override
	public AuditEvent create(AuditEvent event) {
		datastore.add(event);
		return event;
	}

	@Override
	public void delete(AuditEvent event) {
		datastore.remove(event);
	}

	@Override
	public List<AuditEvent> findAll() {
		List<AuditEvent> returnList = new LinkedList<AuditEvent>();
		for(AuditEvent event : datastore) {
			returnList.add(event);
		}
		return returnList;
	}

}
