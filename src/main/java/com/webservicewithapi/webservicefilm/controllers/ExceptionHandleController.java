package com.webservicewithapi.webservicefilm.controllers;

import com.webservicewithapi.webservicefilm.exceptions.BadRequestException;
import com.webservicewithapi.webservicefilm.exceptions.EmptyIsFavoritesSetException;
import com.webservicewithapi.webservicefilm.exceptions.NotValidEmailOrUsernameException;
import com.webservicewithapi.webservicefilm.exceptions.PageNotExistException;
import com.webservicewithapi.webservicefilm.models.Error;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.webjars.NotFoundException;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandleController {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Error> handleMethodNotAllowed(Exception ex)  {
        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
        Error error = new Error(status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Error> handleUnsupportedMediaType(Exception ex)  {
        HttpStatus status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        Error error = new Error(status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<Error> handleNotAcceptable(Exception ex)  {
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        Error error = new Error(status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler({MissingPathVariableException.class, ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public ResponseEntity<Error> handleInternalServerError(Exception ex)  {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Error error = new Error(status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler({BadRequestException.class, NotValidEmailOrUsernameException.class, MissingServletRequestParameterException.class, ServletRequestBindingException.class, TypeMismatchException.class, HttpMessageNotReadableException.class, MethodArgumentNotValidException.class, MissingServletRequestPartException.class, BindException.class})
    public ResponseEntity<Error> handleBadRequest(Exception ex)  {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Error error = new Error(status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler({PageNotExistException.class, NotFoundException.class, EmptyResultDataAccessException.class, EmptyIsFavoritesSetException.class, NoSuchElementException.class})
    public ResponseEntity<Error> handleNotFound(Exception ex)  {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Error error = new Error(status);
        return ResponseEntity.status(status).body(error);
    }


}
