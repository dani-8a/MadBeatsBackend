package com.madbeats.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Feedback")
public class Feedback {
	
	@Id
    private String idMessage;
	private List<String> subject;
	private String message;
	
	public Feedback(String idMessage, List<String> subject, String message) {
		super();
		this.idMessage = idMessage;
		this.subject = subject;
		this.message = message;
	}

	public String getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(String idMessage) {
		this.idMessage = idMessage;
	}

	public List<String> getSubject() {
		return subject;
	}

	public void setSubject(List<String> subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Feedback [idMessage=" + idMessage + ", subject=" + subject + ", message=" + message + "]";
	}
	
}
