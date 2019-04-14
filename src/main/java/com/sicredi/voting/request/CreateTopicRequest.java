package com.sicredi.voting.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateTopicRequest {

	@NotNull
	@Size(min = 1, max = 64)
	private String name;

	
	public String getName() {
		return name;
	}
}
