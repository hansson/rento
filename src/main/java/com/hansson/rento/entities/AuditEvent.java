package com.hansson.rento.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUDIT_EVENTS")
public class AuditEvent {

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mId;
	@Column(name = "MEVENT", length = 64)
	private String mEvent;
	@Column(name = "MEXTRA", length = 64)
	private String mExtra;
	@Column(name = "MDATE")
	private Date mDate;

	public AuditEvent() {
	}

	public AuditEvent(String event, String extra) {
		mEvent = event;
		mExtra = extra;
		mDate = new Date();
	}

	public Integer getId() {
		return mId;
	}

	public void setId(Integer id) {
		mId = id;
	}

	public String getEvent() {
		return mEvent;
	}

	public void setEvent(String event) {
		mEvent = event;
	}

	public Date getDate() {
		return mDate;
	}

	public void setDate(Date added) {
		mDate = added;
	}

	public String getExtra() {
		return mExtra;
	}

	public void setExtra(String extra) {
		mExtra = extra;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof AuditEvent) {
			AuditEvent that = (AuditEvent) obj;
			if(!this.mEvent.equals(that.mEvent)) {
				return false;
			}
			if(!this.mExtra.equals(that.mExtra)) {
				return false;
			}
			if(!this.mDate.equals(that.mDate)) {
				return false;
			}
			return true;
		}
		return false;
	}
	

}