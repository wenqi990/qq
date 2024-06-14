package com.example.practice_9.vo;

import com.fasterxml.jackson.annotation.JsonAlias;

public class UpdatePwdReq {
	
	private String account;
	
	@JsonAlias("old_password")
	private String oldPwd;
	
	@JsonAlias("new_password")
	private String newPwd;

	public UpdatePwdReq() {
		super();
	}

	public UpdatePwdReq(String account, String oldPwd, String newPwd) {
		super();
		this.account = account;
		this.oldPwd = oldPwd;
		this.newPwd = newPwd;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

}
