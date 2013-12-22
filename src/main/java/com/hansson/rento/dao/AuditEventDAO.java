package com.hansson.rento.dao;

import java.util.List;

import com.hansson.rento.entities.AuditEvent;

public interface AuditEventDAO {

	AuditEvent create(AuditEvent event);

	void delete(AuditEvent event);

	List<AuditEvent> findAll();

}
