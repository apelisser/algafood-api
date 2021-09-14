package com.algafood.api.exceptionhandler;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;

@JsonInclude(Include.NON_NULL)
@Builder
public class Problem {
	
	private Integer status;
	private String type;
	private String title;
	private String detail;
	
	private String userMessage;
	private LocalDateTime timestamp;
	
	public Problem(Integer status, String type, String title, String detail, String userMessage, LocalDateTime timestamp) {
		this.status = status;
		this.type = type;
		this.title = title;
		this.detail = detail;
		this.userMessage = userMessage;
		this.timestamp = timestamp;
		
	}
	
	public Problem(Integer status, String title, String userMessage, LocalDateTime timestamp) {
		this(status, null, title, null, userMessage, timestamp);
	}
	
	public Problem(Integer status, String title, String userMessage) {
		this(status, null, title, null, userMessage, LocalDateTime.now());
	}

	public Integer getStatus() {
		return status;
	}
	
	public String getType() {
		return type;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDetail() {
		return detail;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
