package com.erichiroshi.msproduct.resources.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.erichiroshi.msproduct.services.exceptions.DatabaseException;
import com.erichiroshi.msproduct.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
		String error = "Resource not found.";
		Integer status = HttpStatus.NOT_FOUND.value();
		StandardError err = new StandardError(LocalDateTime.now(), status, error, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException ex, HttpServletRequest request) {
		String error = "Database exception";
		Integer status = HttpStatus.BAD_REQUEST.value();
		StandardError err = new StandardError(LocalDateTime.now(), status, error, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> methodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
		String error = "Validation exception";
		Integer status = HttpStatus.UNPROCESSABLE_ENTITY.value();
		ValidationError err = new ValidationError(LocalDateTime.now(), status, error, ex.getMessage(), request.getRequestURI());

		for (FieldError f : ex.getBindingResult().getFieldErrors()) {
			err.addError(f.getField(), f.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<StandardError> amazonService(AmazonServiceException ex, HttpServletRequest request) {
		String error = "AWS Exception.";
		Integer status = HttpStatus.BAD_REQUEST.value();
		StandardError err = new StandardError(LocalDateTime.now(), status, error, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StandardError> amazonClient(AmazonClientException ex, HttpServletRequest request) {
		String error = "AWS Client Exception.";
		Integer status = HttpStatus.BAD_REQUEST.value();
		StandardError err = new StandardError(LocalDateTime.now(), status, error, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<StandardError> illegalArgument(IllegalArgumentException ex, HttpServletRequest request) {
		String error = "Bad request.";
		Integer status = HttpStatus.BAD_REQUEST.value();
		StandardError err = new StandardError(LocalDateTime.now(), status, error, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}