package com.softexpert.interview.core.exceptions.controller;

import com.softexpert.interview.core.exceptions.IntegratedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(IntegratedException.class)
    public ResponseEntity<Object> handlerIntegratedException(IntegratedException ex, WebRequest request) {
        return new ResponseEntity<>(buildResponseBody(
                ex.getMessage(),
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                request.getContextPath()),
                BAD_REQUEST);
    }

    private Map<String, Object> buildResponseBody(String erroMessage, Integer httpStatus, String httpMessage,
                                                  String path) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", erroMessage);
        body.put("status", httpStatus);
        body.put("error", httpMessage);
        body.put("path", path);
        return body;
    }
}