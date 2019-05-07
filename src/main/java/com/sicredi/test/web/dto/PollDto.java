package com.sicredi.test.web.dto;

public class PollDto {

	private int durationInMinutes;

	public PollDto() {}
	
	public PollDto(int durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}

	public int getDurationInMinutes() {
		return durationInMinutes;
	}
	
	public void setDurationInMinutes(int durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}
}
