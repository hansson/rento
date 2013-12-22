package com.hansson.rento.dao;

import java.util.List;

import com.hansson.rento.entities.WeeklyAuditEvent;

public interface WeeklyAuditEventDAO {

	WeeklyAuditEvent create(WeeklyAuditEvent auditEvent);

	void delete(WeeklyAuditEvent auditEvent);

	List<WeeklyAuditEvent> findAll();

}
