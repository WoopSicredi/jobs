package com.sicredi.test.web.dto;

import javax.validation.constraints.NotBlank;

public class TopicCreationDto {

	@NotBlank
	private String name;

	public TopicCreationDto() {}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
