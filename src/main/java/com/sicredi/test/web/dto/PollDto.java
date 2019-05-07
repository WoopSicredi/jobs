package com.sicredi.test.web.dto;

import java.util.Date;

public class PollDto {

	private Date createdOn;
	private int duration;

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
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
}
