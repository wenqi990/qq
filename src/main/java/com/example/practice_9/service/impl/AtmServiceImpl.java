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
	
	// �u�����b�P�@�� Class ���U�A�u�n new �@�ӴN�i�H�A�� class ���O�H�U�ҥi�H�ϥΡC
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // �u��� �i���M�ΥH�U

	@Autowired
	private AtmDao atmDao;

	@Override
	public BasicRes addInfo(String account, String pwd, int balance) {
		// �ˬd�Ѽ�
		// StringUtils.hasText(�r��): �|�ˬd�r��O�_�� null�B�Ŧr��B���ťզr��B�Y�O�ŦX�T�ب䤤�@���A�|�^ false�C
		// �e���[����ĸ���ܤϦV���N��A�Y�r�ꪺ�ˬd���G�O false ���ܡA�N�|�i�� if ����@�϶��C
		// !StringUtils.hasText(account) ���P�� StringUtils.hasText(account) == false�C
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			// �b���K�X�榡���~!!�C
//			System.out.println(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
			return new BasicRes(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getCode(),
					ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
		}
		if (balance < 0) {
//			System.out.println(ResMessage.AMOUNT_ERROR.getMessage()); // ���B���~!!�C
			return new BasicRes(ResMessage.AMOUNT_ERROR.getCode(),
					ResMessage.AMOUNT_ERROR.getMessage());
		}
		// �ˬd�s�W���b���O�_�w�s�b
		// �u�O�Q�T�{�b���O�_�w�s�b�A�S���n��w�s�b���b��������ާ@�A�ҥH�ϥ� existsById�C
		// �Y�O�Q�n��w�s�b����ư�����ާ@�A�i�H�ϥ� findById�C
		if (atmDao.existsById(account)) {
//			System.out.println(ResMessage.ACCOUNT_EXISTS.getMessage()); // �b���w�s�b!!
			return new BasicRes(ResMessage.ACCOUNT_EXISTS.getCode(),
					ResMessage.ACCOUNT_EXISTS.getMessage());
		}
		// �N�K�X�[�K�C
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// Atm atm = new Atm(account , pwd , balance);
		// atmDao.save(atm);
		// �]���ܼ� atm �u�ϥΤ@���A�ҥH�i�H�ΰΦW���O���覡�A��ֵ{���X��ơC
		// encoder.encode(pwd): �N�K�X�[�K(�����ܶýX)�C
		atmDao.save(new Atm(account, encoder.encode(pwd), balance));
//		System.out.println(ResMessage.SUCCESS.getMessage()); // �s�W��T���\!!
		return new BasicRes(ResMessage.SUCCESS.getCode(),
				ResMessage.SUCCESS.getMessage());
	}

	@Override
	public BasicRes updatePwd(String account, String oldPwd, String newPwd) {
		// �ˬd�Ѽ�
		if (!StringUtils.hasText(account) || !StringUtils.hasText(oldPwd) //
				|| !StringUtils.hasText(newPwd)) {
//			System.out.println(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
			return new BasicRes(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getCode(),
					ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
		}
		// �T�{�b���O�_�w�s�b�C
		// 1.�]���|��d��X�Ӫ���ư��K�X���A�ҥH�n�� findById �Ӽ���ơC
		// 2.�u�ϥ� findById �Ӽ���ƪ���]�O�s�b�b��Ʈw���� password ��쪺�ȬO�ϥ� BCryptPasswordEncoder
		// �[�K�᪺�ȡA��S�ʬO�@�˪����e�ȡA�C���[�K��o�쪺���G���|���P�A�ҥH�L�k�����z�L�[�K�L���ȨӤ��
		// ��Ʈw�������e�C
		Optional<Atm> op = atmDao.findById(account);
		// �T�{�b���s�b
		if(op.isEmpty()) { // op.isEmpty() == true ��ܨS����ơC
//			System.out.println(ResMessage.ACCOUNT_NOT_FOUND.getMessage()); // �b�����s�b!!
			return new BasicRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(),
					ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}
		// �q op �����^ Atm ��T
		Atm atm = op.get();
		// ���^�±K�X�P�_
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// encoder.matches( ��l�K�X , �[�K�᪺�K�X)
		// ��l�K�X: �����O�ϥΪ̿�J���K�X�A�Y�ѼƤ��� oldPwd
		// �[�K�᪺�K�X: �����O�z�L BCryptPasswordEncoder �[�K�L���K�X�A�s�b�� DB(DateBase) ��
		if(!encoder.matches(oldPwd, atm.getPwd())) { // �e������ĸ��A��ܱK�X��異�ѡC
//			System.out.println(ResMessage.PASSWORD_ERROR.getMessage()); // �K�X���~!!
			return new BasicRes(ResMessage.PASSWORD_ERROR.getCode(),
					ResMessage.PASSWORD_ERROR.getMessage());
		}
		// �]�w�s�K�X(�[�K�᪺�K�X)
		atm.setPwd(encoder.encode(newPwd));
		// �N�s����Ʀs�^ DB 
		atmDao.save(atm);
//		System.out.println(ResMessage.SUCCESS.getMessage()); // �s�W��T���\!!
		return new BasicRes(ResMessage.SUCCESS.getCode(),
				ResMessage.SUCCESS.getMessage());
	}

	@Override
	public BalanceRes getBalance(String account, String pwd) {
		// �ˬd�Ѽ�
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
//			System.out.println(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
			return new BalanceRes(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getCode(),
					ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
		}
		// �ˬd�b���O�_�w�s�b
		// �]���ݭn��w�s�b����Ƨ@����ާ@(���l�B)�A�ҥH�ϥ� findById�C
		Optional<Atm> op = atmDao.findById(account);
		// �T�{�b���w�s�b
		if(op.isEmpty()) { // op.isEmpty() == true ��ܨ��^����ƬO�Ū��A�N�O�b�����s�b�C
//			System.out.println(ResMessage.ACCOUNT_NOT_FOUND.getMessage());
			return new BalanceRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(),
					ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}
		// ���^�Q Optional ���^ Atm �����
		Atm atm = op.get();
		// ���K�X
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(!encoder.matches(pwd, atm.getPwd())) { // == false ��ܤ�異�ѡC
//			System.out.println(ResMessage.PASSWORD_ERROR.getMessage());
			return new BalanceRes(ResMessage.PASSWORD_ERROR.getCode(),
					ResMessage.PASSWORD_ERROR.getMessage());
		}
//		System.out.printf("%s: %d \n" , atm.getAccount(), atm.getBalance());
		return new BalanceRes(ResMessage.SUCCESS.getCode(),
				ResMessage.SUCCESS.getMessage());
	}

	@Override //�s��
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
//		// �T�{�b���s�b
//		if(op.isEmpty()) { // op.isEmpty() == true ��ܨS����ơC
////			System.out.println(ResMessage.ACCOUNT_NOT_FOUND.getMessage());
//			return new BalanceRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(),
//					ResMessage.ACCOUNT_NOT_FOUND.getMessage());
//		}
//		Atm atm = op.get();
//		
////		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		if(!encoder.matches(pwd, atm.getPwd())) { // == false ��ܤ�異�ѡC
////			System.out.println(ResMessage.PASSWORD_ERROR.getMessage());
//			return new BalanceRes(ResMessage.PASSWORD_ERROR.getCode(),
//					ResMessage.PASSWORD_ERROR.getMessage());
//		}
//		// �s��
//		atm.setBalance(atm.getBalance() + depositamount);
//		atmDao.save(atm);
////		System.out.println(ResMessage.SUCCESS.getMessage());
//		System.out.printf("%s: %d \n" , atm.getAccount(), atm.getBalance() , +depositamount);
//		return new BalanceRes(ResMessage.SUCCESS.getCode(),
//				ResMessage.SUCCESS.getMessage());
	}

	@Override //����
	public BalanceRes withdraw(String account, String pwd, int withdrawamount) {
		return depositOrwithdraw(account , pwd , withdrawamount, false);
	}
	
	// �쥻�� deposit �M withdraw ����k��ѼƥN���N�䳣�O�b���B�K�X�B���B�A�Y�O�N��Ӥ�k�X��
	// �ڥ��N�����D�O�ާ@�s���٬O���ڡA�]���ݭn�h�@�ӥ��L�ѼƨӽT�w�O�ާ@�s���٬O���ڡC
	// isDeposit ���ȬO true �ɡA��ܬO�s��: �ȬO false �ɡA�N��ܴ��ڡC
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
		// �T�{�b���s�b
		if(op.isEmpty()) { // op.isEmpty() == true ��ܨS����ơC
//			System.out.println(ResMessage.ACCOUNT_NOT_FOUND.getMessage());
			return new BalanceRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(),
					ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}
		
		Atm atm = op.get();
		
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(!encoder.matches(pwd, atm.getPwd())) { // == false ��ܤ�異�ѡC
//			System.out.println(ResMessage.PASSWORD_ERROR.getMessage());
			return new BalanceRes(ResMessage.PASSWORD_ERROR.getCode(),
					ResMessage.PASSWORD_ERROR.getMessage());
		}
		
		if(!isDeposit){ // !isDeposit ���P�� isDeposit == false 
			// �]���O�ާ@���ڡA�ҥH�n�ˬd�l�B�O�_�����C
			if(atm.getBalance() < amount) {
//				System.out.println(ResMessage.INSUFFICIENT_BALANCE.getMessage()); // �l�B����!!
				return new BalanceRes(ResMessage.INSUFFICIENT_BALANCE.getCode(),
						ResMessage.INSUFFICIENT_BALANCE.getMessage());
			}
			// ��쥻�������B amount �ܦ��t���A�O�]������ާ@�O atm.getBalance + amount
			// �]���O���ڡA�ҥH�O�n�[�`�t�ƪ��B�C
			amount = -amount;
		}
		// ����
//		atm.setBalance(atm.getBalance() + (-amount));
		// �s��
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
