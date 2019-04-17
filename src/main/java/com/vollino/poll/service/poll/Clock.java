package com.vollino.poll.service.poll;

import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

/**
 * @author Bruno Vollino
 */
@Component
public class Clock {

    public ZonedDateTime now() {
        return ZonedDateTime.now();
    }
}
