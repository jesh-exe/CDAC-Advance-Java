package com.app.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiResponse {

	private String message;
	private LocalDateTime timeStamp;
	
	public ApiResponse(String message)
	{
		this.message = message;
		this.timeStamp = LocalDateTime.now();
	}
}
