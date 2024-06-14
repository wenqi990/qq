package com.example.practice_9.vo;

public class BasicRes {

	private int Statuscode;

	private String message;

	public BasicRes() {
		super();
	}
	

	public BasicRes(int statuscode, String message) {
		super();
		this.Statuscode = statuscode;
		this.message = message;
	}

	
	public int getStatuscode() {
		return Statuscode;
	}

	public void setStatuscode(int statuscode) {
		Statuscode = statuscode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
