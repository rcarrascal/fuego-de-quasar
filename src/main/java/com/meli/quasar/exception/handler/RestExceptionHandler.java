package com.meli.quasar.exception.handler;

import com.meli.quasar.exception.HelpMessageException;
import com.meli.quasar.model.response.Response;
import com.meli.quasar.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Locale;

@RestControllerAdvice
public class RestExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(Exception.class)
    public Response exceptionHandler(Exception exception) {
        return new Response(HttpStatus.INTERNAL_SERVER_ERROR.name(), messageSource.getMessage(Constants.EXCEPTION_EXCEPTION, null, Locale.getDefault()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        return new Response(HttpStatus.BAD_REQUEST.name(), messageSource.getMessage(Constants.EXCEPTION_METHODARGUMENTNOTVALIDEXCEPTION, null, Locale.getDefault()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException exception) {
        return new Response(HttpStatus.BAD_REQUEST.name(), messageSource.getMessage(Constants.EXCEPTION_HTTPMESSAGENOTREADABLEEXCEPTION, null, Locale.getDefault()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Response methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException exception) {
        return new Response(HttpStatus.BAD_REQUEST.name(), messageSource.getMessage(Constants.EXCEPTION_METHODARGUMENTTYPEMISMATCHEXCEPTION, null, Locale.getDefault()));
    }

    @ExceptionHandler(HelpMessageException.class)
    public Response generalExceptionHandler(HelpMessageException exception) {
        return new Response(HttpStatus.NOT_FOUND.name(), messageSource.getMessage(exception.getMessage(), null, Locale.getDefault()));
    }

}
