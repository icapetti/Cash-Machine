package com.caixaeletronico.controllers;

import com.caixaeletronico.database.AccountJpaDAO;
import com.caixaeletronico.models.Account;

public class AccessAccountController {

	//receber os dados da conta e consultar no banco para validar o acesso
	
	public static Account AccessAccount(int account_number, String account_password) {
		
		if(Account.accessAccount(account_number, account_password)) {
			Account account = AccountJpaDAO.getById(account_number);
			return account;
		}else {
			return null;
		}
	}
}
