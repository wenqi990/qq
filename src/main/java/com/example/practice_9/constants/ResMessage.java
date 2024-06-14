package com.example.practice_9.constants;

public enum ResMessage {
	
	SUCCESS(200 , "Success!!"), //
	ACCOUNT_OR_PASSWORD_ERROR(400 , "Account or password error!!"), //
	AMOUNT_ERROR(400 , "Amount error!!"), //
	ACCOUNT_EXISTS(400 , "Account exists!!"), // 
	ACCOUNT_NOT_FOUND(404 , "Account not found!!"), //
	PASSWORD_ERROR(400 , "Password error!!") , //
	INSUFFICIENT_BALANCE(400 , "Insufficient balance");

	private int code; // HTTP Âà´«½X
	
	private String message ;

	private ResMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
}
