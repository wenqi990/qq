package com.example.practice_9;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.practice_9.service.ifs.AtmService;

@SpringBootTest
public class AtmTests {
	
	@Autowired
	private AtmService atmService;

	@Test
	public void addInfoTest() {
		atmService.addInfo("A01" , "AA123" , 1000);
	}
	
	@Test
	public void updatePwdTest() {
		atmService.updatePwd("A01", "BB456", "AA123");
	}
	
	@Test
	public void getBalanceTest() {
		atmService.getBalance("A01", "AA123");
	}
	
	@Test
	public void depositamountTest() {
		atmService.deposit("A01", "AA123", 500);
	}
	
	@Test
	public void withdrawamount() {
		atmService.withdraw("A01", "AA123", 500);
	}
}
