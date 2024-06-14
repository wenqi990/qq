package com.example.practice_9.vo;

//import com.fasterxml.jackson.annotation.JsonProperty;

public class AddInfoReq extends BasicReq {

	private int balance;

	public int getBalance() {
		return balance;
	}

	// 因為 req 只會用到 get 方法而已，所以不需要屬性的 set 方法以及帶有參數的建構方法。
	// 帶有參數的建構方法其行為等同於屬性的 set 方法。
	// 基本上，只要是 req，只需要寫屬性的 get 方法即可，不需要屬性的 set 方法以及帶有參數的建構方法。
//	public void setBalance(int balance) {
//		this.balance = balance;
//	}

}
