package br.com.sicred.voting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.AbstractMap;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

  private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  /**
   * Global Exception handler for all exceptions.
   */
  @ExceptionHandler
  public ResponseEntity<AbstractMap.SimpleEntry<String, String>> handle(
          Exception exception) {
    // general exception
    LOG.error("Exception: Unable to process this request. ", exception);
    AbstractMap.SimpleEntry<String, String> response =
        new AbstractMap.SimpleEntry<>("message", exception.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }
}
