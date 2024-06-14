package com.example.practice_9.service.ifs;

import com.example.practice_9.vo.BasicRes;
import com.example.practice_9.vo.BalanceRes;

public interface AtmService {
	
	public BasicRes addInfo(String account , String pwd , int balance);
	
	public BasicRes updatePwd(String account , String oldPwd , String newPwd);
	
	public BalanceRes getBalance(String account , String pwd);
	
	public BalanceRes deposit(String account , String pwd , int depositamount);
	
	public BalanceRes withdraw(String account , String pwd , int withdrawamount);

}
