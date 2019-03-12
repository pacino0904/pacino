package com.event.model;


public class Event {
	public int id;
	public String occTime;
	public String locale;
	public String department;
	public String level;
	public String discPerson;
	public String discTime;
	public String handlePerson;
	public String eventDesc;
	public String effBus;
	public String incidence;
	public String effTime;
	public String eventHandle;
	public String eventReason;
	public String eventResult;
	public String eventCount;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOccTime() {
		return occTime;
	}
	public void setOccTime(String occTime) {
		this.occTime = occTime;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getDiscTime() {
		return discTime;
	}
	public void setDiscTime(String discTime) {
		this.discTime = discTime;
	}
	public String getEffBus() {
		return effBus;
	}
	public void setEffBus(String effBus) {
		this.effBus = effBus;
	}
	public String getEffTime() {
		return effTime;
	}

	public void setEffTime(String effTime) {
		this.effTime = effTime;
	}
	
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getDiscPerson() {
		return discPerson;
	}

	public void setDiscPerson(String discPerson) {
		this.discPerson = discPerson;
	}

	public String getHandlePerson() {
		return handlePerson;
	}

	public void setHandlePerson(String handlePerson) {
		this.handlePerson = handlePerson;
	}

	public String getIncidence() {
		return incidence;
	}

	public void setIncidence(String incidence) {
		this.incidence = incidence;
	}

	public String getEventHandle() {
		return eventHandle;
	}

	public void setEventHandle(String eventHandle) {
		this.eventHandle = eventHandle;
	}

	public String getEventReason() {
		return eventReason;
	}

	public void setEventReason(String eventReason) {
		this.eventReason = eventReason;
	}

	public String getEventResult() {
		return eventResult;
	}

	public void setEventResult(String eventResult) {
		this.eventResult = eventResult;
	}
	
	public String getEventCount() {
		return eventCount;
	}
	public void setEventCount(String eventCount) {
		this.eventCount = eventCount;
	}
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
}
