package com.example.practice_9.vo;

import com.fasterxml.jackson.annotation.JsonAlias;

public class BasicReq {

	private String account;

	// @JsonProperty("password"): 可以將外部請求中 key 和括號中的字串 mapping，並將值賦予到變數中，但只能有一個。
	// @JsonAlias({"password" , "pwd"}): 效果同 @JsonProperty，可以有多個 key 的 mapping。
	// 上面兩個注釋擇一使用
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
