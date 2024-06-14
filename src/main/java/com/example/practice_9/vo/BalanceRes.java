package com.example.practice_9.vo;

public class BalanceRes extends BasicRes {
	
//	private int statuscode;
//
//	private String message;
	
//	private BasicRes basicRes; // Ä~©Ó
	
	private String account;
	
	private int balance;

	public BalanceRes() {
		super();
	}

	public BalanceRes(int statuscode, String message) {
		super(statuscode, message);
	}

	public BalanceRes(int statuscode, String message , String account, int balance) {
		super(statuscode, message);
		this.account = account;
		this.balance = balance;
	}


	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	

}
