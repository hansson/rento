package com.hansson.rento.dao;

import java.util.List;

import com.hansson.rento.entities.MonthlyAuditEvent;


public interface MonthlyAuditEventDAO {

	MonthlyAuditEvent create(MonthlyAuditEvent auditEvent);

	void delete(MonthlyAuditEvent auditEvent);

	List<MonthlyAuditEvent> findAll();

}
