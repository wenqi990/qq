package com.example.practice_9.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "atm")
public class Atm {

	@Id
	@Column(name = "account")
	private String account;

	@Column(name = "password")
	private String pwd; // PWD ¬°±Ó·P¦r¨¥¡C

	@Column(name = "balance")
	private int balance;

	public Atm() {
		super();
	}

	public Atm(String account, String pwd, int balance) {
		super();
		this.account = account;
		this.pwd = pwd;
		this.balance = balance;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

}
