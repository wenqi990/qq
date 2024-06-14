package com.example.practice_9.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.practice_9.service.ifs.AtmService;
import com.example.practice_9.vo.AddInfoReq;
import com.example.practice_9.vo.BasicReq;
import com.example.practice_9.vo.BasicRes;
import com.example.practice_9.vo.DepositOrWithdrawReq;
import com.example.practice_9.vo.BalanceRes;
import com.example.practice_9.vo.UpdatePwdReq;

// @RestController 包含在 @Controller 和 @RreponseBody 
// @Controller 是個將此類別交出 Spring boot 託管成 Controller 物件。
// @RespojseBody: 可以將自定義的物件(response)轉換成 JSON 格式傳輸給外部。
// 加了 @RestController 後，就不需要在AddInfoRes(或其他的 xxxRes) 前面加上 @ResponseBody。
@RestController
public class AtmController {
	
	@Autowired
	public AtmService atmService;
	
	/**
	 * 1. @PostMapping: 表示請求方法的 Http method 是 POST<br>
	 * 2. value = "atm/add_info": 表示該請求的路徑(URL)，自定義<br>
	 * 3. @RequestBody: 可以把外部請求中的 JSON 物件(key-value) 對應到自定義的 AddInfoReq 中的屬性名稱中，
	 * 並把值賦予到對應的變數裡。
	 * */
	// 下一行的另一種寫法: @RequestMapping(value = "atm/add_info", method = RequestMethod.POST)
	@PostMapping(value = "atm/add_info") // 變數名稱
	public BasicRes addInfo(@RequestBody AddInfoReq req) {
//		AddInfoRes res = atmService.addInfo(req.getAccount(), req.getPwd(), req.getBalance());
		return atmService.addInfo(req.getAccount(), req.getPwd(), req.getBalance());
	}
	
	@PostMapping(value = "atm/update_password") // 變數名稱
	public BasicRes updatePwd(@RequestBody UpdatePwdReq req) {
		return atmService.updatePwd(req.getAccount(), req.getOldPwd(), req.getNewPwd());
		
	}
	
	@PostMapping(value = "atm/get_balance")
	public BalanceRes getBalance(@RequestBody BasicReq req) {
		return atmService.getBalance(req.getAccount(), req.getPwd());
	}
	
	@PostMapping(value = "atm/deposit")
	public BalanceRes deposit(@RequestBody DepositOrWithdrawReq req) {
		return atmService.deposit(req.getAccount(), req.getPwd(), req.getAmount());
	}
	
	@PostMapping(value = "atm/withdraw")
	public BalanceRes withdraw(@RequestBody DepositOrWithdrawReq req) {
		return atmService.withdraw(req.getAccount(), req.getPwd(), req.getAmount());
	}

}
