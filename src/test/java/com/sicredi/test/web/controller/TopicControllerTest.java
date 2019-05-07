package com.sicredi.test.web.controller;

import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sicredi.test.persistence.service.ITopicService;
import com.sicredi.test.persistence.service.IVoteService;
import com.sicredi.test.web.converter.PollResultsConverter;
import com.sicredi.test.web.validator.TopicValidator;

@RunWith(MockitoJUnitRunner.class)
public class TopicControllerTest {

    @Mock
    private ITopicService topicService;
    @Mock
    private IVoteService voteService;
    @Mock
    private TopicValidator topicValidator;
    @Mock
    private PollResultsConverter pollResultsConverter;
    
	@InjectMocks
	private TopicController topicController;
	
	@Test
	public void shouldCallTopicServiceForCreation() {
		//when
//		topicController.create(resource, response);
	}
}
