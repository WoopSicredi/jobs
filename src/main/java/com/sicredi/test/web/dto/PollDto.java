package com.sicredi.test.web.dto;

import java.util.Date;

public class PollDto {

	private Date createdOn;
	private int duration;

	public PollDto() {}
	
	public PollDto(Date createdOn, int duration) {
		this.createdOn = createdOn;
		this.duration = duration;
	}

	public Date getCreatedOn() {
		return createdOn;
	}
	
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public int getDurationInMinutes() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
}
