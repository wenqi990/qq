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

// @RestController �]�t�b @Controller �M @RreponseBody 
// @Controller �O�ӱN�����O��X Spring boot �U�ަ� Controller ����C
// @RespojseBody: �i�H�N�۩w�q������(response)�ഫ�� JSON �榡�ǿ鵹�~���C
// �[�F @RestController ��A�N���ݭn�bAddInfoRes(�Ψ�L�� xxxRes) �e���[�W @ResponseBody�C
@RestController
public class AtmController {
	
	@Autowired
	public AtmService atmService;
	
	/**
	 * 1. @PostMapping: ��ܽШD��k�� Http method �O POST<br>
	 * 2. value = "atm/add_info": ��ܸӽШD�����|(URL)�A�۩w�q<br>
	 * 3. @RequestBody: �i�H��~���ШD���� JSON ����(key-value) ������۩w�q�� AddInfoReq �����ݩʦW�٤��A
	 * �ç�Ƚᤩ��������ܼƸ̡C
	 * */
	// �U�@�檺�t�@�ؼg�k: @RequestMapping(value = "atm/add_info", method = RequestMethod.POST)
	@PostMapping(value = "atm/add_info") // �ܼƦW��
	public BasicRes addInfo(@RequestBody AddInfoReq req) {
//		AddInfoRes res = atmService.addInfo(req.getAccount(), req.getPwd(), req.getBalance());
		return atmService.addInfo(req.getAccount(), req.getPwd(), req.getBalance());
	}
	
	@PostMapping(value = "atm/update_password") // �ܼƦW��
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
