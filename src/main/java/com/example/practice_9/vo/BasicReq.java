package com.example.practice_9.vo;

import com.fasterxml.jackson.annotation.JsonAlias;

public class BasicReq {

	private String account;

	// @JsonProperty("password"): �i�H�N�~���ШD�� key �M�A�������r�� mapping�A�ñN�Ƚᤩ���ܼƤ��A���u�঳�@�ӡC
	// @JsonAlias({"password" , "pwd"}): �ĪG�P @JsonProperty�A�i�H���h�� key �� mapping�C
	// �W����Ӫ`���ܤ@�ϥ�
//		@JsonProperty("password") 
	@JsonAlias({ "password", "pwd" })
	private String pwd;

	public BasicReq() {
		super();
	}

	public BasicReq(String account, String pwd) {
		super();
		this.account = account;
		this.pwd = pwd;
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

}
