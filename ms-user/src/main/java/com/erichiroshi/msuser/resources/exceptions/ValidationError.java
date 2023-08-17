package com.erichiroshi.msuser.resources.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ValidationError extends StandardError {

	private List<FieldMessage> errors = new ArrayList<>();

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

	public ValidationError(LocalDateTime timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

}