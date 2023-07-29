package com.erichiroshi.dscatalog.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class EmailDTO implements Serializable {

	private String fromEmail;
	private String fromName;
	private String toEmail;
	private String toName;
	private String subject;
	private String body;

}