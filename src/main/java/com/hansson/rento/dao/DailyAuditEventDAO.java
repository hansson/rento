package com.hansson.rento.dao;

import java.util.List;

import com.hansson.rento.entities.AuditEvent;
import com.hansson.rento.entities.DailyAuditEvent;

public interface DailyAuditEventDAO {

	DailyAuditEvent create(DailyAuditEvent auditEvent);

	void delete(DailyAuditEvent auditEvent);

	List<DailyAuditEvent> findAll();

}
