package com.vollino.poll.service.poll.business.exception;

import com.vollino.poll.service.exception.business.ClientErrorException;
import com.vollino.poll.service.poll.business.domain.Poll;

/**
 * @author Bruno Vollino
 */
public class ClosedPollException extends ClientErrorException {

    private static final long serialVersionUID = -2079493897660047529L;

    public ClosedPollException(Poll poll) {
        super(String.format("Poll with id=%d has closed at %s", poll.getId(), poll.getEndDate().toString()));
    }
}
