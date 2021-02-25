package com.example.notepad.model;

public class ErrorMessage {
	private String errorMessage;
	private String errorURL;
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorURL() {
		return errorURL;
	}
	public void setErrorURL(String errorURL) {
		this.errorURL = errorURL;
	}
	public ErrorMessage(String errorMessage, String errorURL) {
		super();
		this.errorMessage = errorMessage;
		this.errorURL = errorURL;
	}
	@Override
	public String toString() {
		return "ErrorMessage [errorMessage=" + errorMessage + ", errorURL=" + errorURL + "]";
	}
	
	
}
