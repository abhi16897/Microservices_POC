package com.seveneleven.employeedetails.model;

public class ResponseTransfer {
	String text;
	public ResponseTransfer() {
		
	}
	public ResponseTransfer(String text) {
		this.text=text;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "ResponseTransfer [text=" + text + "]";
	}
}
