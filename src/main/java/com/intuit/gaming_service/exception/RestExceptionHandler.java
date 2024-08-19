package com.intuit.gaming_service.exception;

import jakarta.persistence.EntityNotFoundException;
import com.intuit.gaming_service.dto.ResponseDto;

import org.hibernate.cache.CacheException;
import org.springframework.data.repository.core.RepositoryCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ResponseDto> handleIllegalArgumentException(String message) {
    ResponseDto responseDto = new ResponseDto(null, message, false);
    return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NullPointerException.class)
  public ResponseEntity<ResponseDto> handleNullPointerException(String message) {
    ResponseDto responseDto = new ResponseDto(null, message, false);
    return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(CacheException.class)
  public ResponseEntity<ResponseDto> CacheException(String message) {
    ResponseDto responseDto = new ResponseDto(null, message, false);
    return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ResponseDto> RepositoryCreationException(String message) {
    ResponseDto responseDto = new ResponseDto(null, message, false);
    return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseDto> handleGlobalException(Exception ex) {
    ResponseDto responseDto = new ResponseDto(null, ex.getMessage(), false);
    return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
