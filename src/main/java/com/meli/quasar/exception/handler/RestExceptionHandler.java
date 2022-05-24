package com.meli.quasar.exception.handler;

import com.meli.quasar.exception.HelpMessageException;
import com.meli.quasar.model.response.Response;
import com.meli.quasar.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
public class RestExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(Exception.class)
    public HttpEntity<Response> exceptionHandler(Exception exception) {
        Response response = new Response(HttpStatus.INTERNAL_SERVER_ERROR.name(), messageSource.getMessage(Constants.EXCEPTION_EXCEPTION, null, Locale.getDefault()));
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HelpMessageException.class)
    public HttpEntity<Response> generalExceptionHandler(Exception exception) {
        Response response = new Response(HttpStatus.NOT_FOUND.name(), messageSource.getMessage(exception.getMessage(), null, Locale.getDefault()));
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

}
