package com.sidj.bcs.dto;

import java.io.Serializable;

public class ResponseDto<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private int responseCode;
	private String responseDescription;
	private int exceptionCode;
	private String exceptionDescription;
	private T body;
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseDescription() {
		return responseDescription;
	}
	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}
	public int getExceptionCode() {
		return exceptionCode;
	}
	public void setExceptionCode(int exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
	public String getExceptionDescription() {
		return exceptionDescription;
	}
	public void setExceptionDescription(String exceptionDescription) {
		this.exceptionDescription = exceptionDescription;
	}
	public T getBody() {
		return body;
	}
	public void setBody(T body) {
		this.body = body;
	}
	
	

}
