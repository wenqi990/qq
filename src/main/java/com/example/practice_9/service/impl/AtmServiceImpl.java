package com.example.practice_9.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.practice_9.constants.ResMessage;
import com.example.practice_9.entity.Atm;
import com.example.practice_9.repository.AtmDao;
import com.example.practice_9.service.ifs.AtmService;
import com.example.practice_9.vo.BasicRes;
import com.example.practice_9.vo.BalanceRes;

@Service
public class AtmServiceImpl implements AtmService {
	
	// 工具類在同一個 Class 底下，只要 new 一個就可以，該 class 類別以下皆可以使用。
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // 工具組 可全套用以下

	@Autowired
	private AtmDao atmDao;

	@Override
	public BasicRes addInfo(String account, String pwd, int balance) {
		// 檢查參數
		// StringUtils.hasText(字串): 會檢查字串是否為 null、空字串、全空白字串、若是符合三種其中一項，會回 false。
		// 前面加個驚嘆號表示反向的意思，若字串的檢查結果是 false 的話，就會進到 if 的實作區塊。
		// !StringUtils.hasText(account) 等同於 StringUtils.hasText(account) == false。
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			// 帳號密碼格式錯誤!!。
//			System.out.println(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
			return new BasicRes(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getCode(),
					ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
		}
		if (balance < 0) {
//			System.out.println(ResMessage.AMOUNT_ERROR.getMessage()); // 金額錯誤!!。
			return new BasicRes(ResMessage.AMOUNT_ERROR.getCode(),
					ResMessage.AMOUNT_ERROR.getMessage());
		}
		// 檢查新增的帳號是否已存在
		// 只是想確認帳號是否已存在，沒有要對已存在的帳號做後續操作，所以使用 existsById。
		// 若是想要對已存在的資料做後續操作，可以使用 findById。
		if (atmDao.existsById(account)) {
//			System.out.println(ResMessage.ACCOUNT_EXISTS.getMessage()); // 帳號已存在!!
			return new BasicRes(ResMessage.ACCOUNT_EXISTS.getCode(),
					ResMessage.ACCOUNT_EXISTS.getMessage());
		}
		// 將密碼加密。
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// Atm atm = new Atm(account , pwd , balance);
		// atmDao.save(atm);
		// 因為變數 atm 只使用一次，所以可以用匿名類別的方式，減少程式碼行數。
		// encoder.encode(pwd): 將密碼加密(明文變亂碼)。
		atmDao.save(new Atm(account, encoder.encode(pwd), balance));
//		System.out.println(ResMessage.SUCCESS.getMessage()); // 新增資訊成功!!
		return new BasicRes(ResMessage.SUCCESS.getCode(),
				ResMessage.SUCCESS.getMessage());
	}

	@Override
	public BasicRes updatePwd(String account, String oldPwd, String newPwd) {
		// 檢查參數
		if (!StringUtils.hasText(account) || !StringUtils.hasText(oldPwd) //
				|| !StringUtils.hasText(newPwd)) {
//			System.out.println(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
			return new BasicRes(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getCode(),
					ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
		}
		// 確認帳號是否已存在。
		// 1.因為會對查找出來的資料做密碼比對，所以要用 findById 來撈資料。
		// 2.只使用 findById 來撈資料的原因是存在在資料庫中的 password 欄位的值是使用 BCryptPasswordEncoder
		// 加密後的值，其特性是一樣的內容值，每次加密後得到的結果都會不同，所以無法直接透過加密過的值來比對
		// 資料庫中的內容。
		Optional<Atm> op = atmDao.findById(account);
		// 確認帳號存在
		if(op.isEmpty()) { // op.isEmpty() == true 表示沒有資料。
//			System.out.println(ResMessage.ACCOUNT_NOT_FOUND.getMessage()); // 帳號不存在!!
			return new BasicRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(),
					ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}
		// 從 op 中取回 Atm 資訊
		Atm atm = op.get();
		// 取回舊密碼判斷
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// encoder.matches( 原始密碼 , 加密後的密碼)
		// 原始密碼: 指的是使用者輸入的密碼，即參數中的 oldPwd
		// 加密後的密碼: 指的是透過 BCryptPasswordEncoder 加密過的密碼，存在於 DB(DateBase) 中
		if(!encoder.matches(oldPwd, atm.getPwd())) { // 前面有驚嘆號，表示密碼比對失敗。
//			System.out.println(ResMessage.PASSWORD_ERROR.getMessage()); // 密碼錯誤!!
			return new BasicRes(ResMessage.PASSWORD_ERROR.getCode(),
					ResMessage.PASSWORD_ERROR.getMessage());
		}
		// 設定新密碼(加密後的密碼)
		atm.setPwd(encoder.encode(newPwd));
		// 將新的資料存回 DB 
		atmDao.save(atm);
//		System.out.println(ResMessage.SUCCESS.getMessage()); // 新增資訊成功!!
		return new BasicRes(ResMessage.SUCCESS.getCode(),
				ResMessage.SUCCESS.getMessage());
	}

	@Override
	public BalanceRes getBalance(String account, String pwd) {
		// 檢查參數
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
//			System.out.println(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
			return new BalanceRes(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getCode(),
					ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
		}
		// 檢查帳號是否已存在
		// 因為需要對已存在的資料作後續操作(取餘額)，所以使用 findById。
		Optional<Atm> op = atmDao.findById(account);
		// 確認帳號已存在
		if(op.isEmpty()) { // op.isEmpty() == true 表示取回的資料是空的，就是帳號不存在。
//			System.out.println(ResMessage.ACCOUNT_NOT_FOUND.getMessage());
			return new BalanceRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(),
					ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}
		// 取回被 Optional 取回 Atm 的資料
		Atm atm = op.get();
		// 比對密碼
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(!encoder.matches(pwd, atm.getPwd())) { // == false 表示比對失敗。
//			System.out.println(ResMessage.PASSWORD_ERROR.getMessage());
			return new BalanceRes(ResMessage.PASSWORD_ERROR.getCode(),
					ResMessage.PASSWORD_ERROR.getMessage());
		}
//		System.out.printf("%s: %d \n" , atm.getAccount(), atm.getBalance());
		return new BalanceRes(ResMessage.SUCCESS.getCode(),
				ResMessage.SUCCESS.getMessage());
	}

	@Override //存款
	public BalanceRes deposit(String account, String pwd, int depositamount) {
		return depositOrwithdraw(account , pwd , depositamount, true);
//		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
////			System.out.println(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
//			return new BalanceRes(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getCode(),
//					ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
//		}
//		
//		if(depositamount <=0) {
////			System.out.println(ResMessage.AMOUNT_ERROR.getMessage());
//			return new BalanceRes(ResMessage.AMOUNT_ERROR.getCode(),
//					ResMessage.AMOUNT_ERROR.getMessage());
//		}
//		
//		Optional<Atm> op = atmDao.findById(account);
//		// 確認帳號存在
//		if(op.isEmpty()) { // op.isEmpty() == true 表示沒有資料。
////			System.out.println(ResMessage.ACCOUNT_NOT_FOUND.getMessage());
//			return new BalanceRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(),
//					ResMessage.ACCOUNT_NOT_FOUND.getMessage());
//		}
//		Atm atm = op.get();
//		
////		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		if(!encoder.matches(pwd, atm.getPwd())) { // == false 表示比對失敗。
////			System.out.println(ResMessage.PASSWORD_ERROR.getMessage());
//			return new BalanceRes(ResMessage.PASSWORD_ERROR.getCode(),
//					ResMessage.PASSWORD_ERROR.getMessage());
//		}
//		// 存款
//		atm.setBalance(atm.getBalance() + depositamount);
//		atmDao.save(atm);
////		System.out.println(ResMessage.SUCCESS.getMessage());
//		System.out.printf("%s: %d \n" , atm.getAccount(), atm.getBalance() , +depositamount);
//		return new BalanceRes(ResMessage.SUCCESS.getCode(),
//				ResMessage.SUCCESS.getMessage());
	}

	@Override //提領
	public BalanceRes withdraw(String account, String pwd, int withdrawamount) {
		return depositOrwithdraw(account , pwd , withdrawamount, false);
	}
	
	// 原本的 deposit 和 withdraw 的方法其參數代表的意思都是帳號、密碼、金額，若是將兩個方法合併
	// 根本就不知道是操作存款還是提款，因此需要多一個布林參數來確定是操作存款還是提款。
	// isDeposit 的值是 true 時，表示是存款: 值是 false 時，就表示提款。
	private BalanceRes depositOrwithdraw(String account , String pwd , int amount , boolean isDeposit) {
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
//			System.out.println(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
			return new BalanceRes(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getCode(),
					ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
		}
		
		if(amount <=0) {
//			System.out.println(ResMessage.AMOUNT_ERROR.getMessage());
			return new BalanceRes(ResMessage.AMOUNT_ERROR.getCode(),
					ResMessage.AMOUNT_ERROR.getMessage());
		}
		
		Optional<Atm> op = atmDao.findById(account);
		// 確認帳號存在
		if(op.isEmpty()) { // op.isEmpty() == true 表示沒有資料。
//			System.out.println(ResMessage.ACCOUNT_NOT_FOUND.getMessage());
			return new BalanceRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(),
					ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}
		
		Atm atm = op.get();
		
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(!encoder.matches(pwd, atm.getPwd())) { // == false 表示比對失敗。
//			System.out.println(ResMessage.PASSWORD_ERROR.getMessage());
			return new BalanceRes(ResMessage.PASSWORD_ERROR.getCode(),
					ResMessage.PASSWORD_ERROR.getMessage());
		}
		
		if(!isDeposit){ // !isDeposit 等同於 isDeposit == false 
			// 因為是操作提款，所以要檢查餘額是否足夠。
			if(atm.getBalance() < amount) {
//				System.out.println(ResMessage.INSUFFICIENT_BALANCE.getMessage()); // 餘額不足!!
				return new BalanceRes(ResMessage.INSUFFICIENT_BALANCE.getCode(),
						ResMessage.INSUFFICIENT_BALANCE.getMessage());
			}
			// 把原本正的金額 amount 變成負的，是因為後續操作是 atm.getBalance + amount
			// 因為是提款，所以是要加總負數金額。
			amount = -amount;
		}
		// 提款
//		atm.setBalance(atm.getBalance() + (-amount));
		// 存款
		atm.setBalance(atm.getBalance() + amount);
		atmDao.save(atm);
//		System.out.println(ResMessage.SUCCESS.getMessage());
//		System.out.printf("%s: %d \n" , atm.getAccount(), atm.getBalance() - withdrawamount);
		return new BalanceRes(ResMessage.SUCCESS.getCode(),
				ResMessage.SUCCESS.getMessage());
	}
	
//	private BasicRes checkParams(String account, String pwd) {
//		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
//			return new BalanceRes(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getCode(),
//					ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
//	    }
//		return null;
//	}
}
