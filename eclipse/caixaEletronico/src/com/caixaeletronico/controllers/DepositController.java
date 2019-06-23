package com.caixaeletronico.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.caixaeletronico.models.Account;
import com.caixaeletronico.models.Transaction;

public class DepositController {
	
	public static boolean Deposit(Account account, String transaction_amount) {
		
		/*inicializacoes*/

		Transaction transaction = new Transaction();
		Date date_now = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String transaction_date = dateFormat.format(date_now);
        Float transaction_amount2 = Float.valueOf(transaction_amount);
        
		//define os dados da transaction
		transaction.setTransaction_date(transaction_date);
		transaction.setTransaction_amount(transaction_amount2);
		transaction.setTransaction_type("DEPOSIT");
		transaction.setTransaction_currenct_balance(account.getCurrent_balance() + transaction_amount2);
		transaction.setAccount(account);
		
		//define novo valor do current_balance do objeto account
		account.setCurrent_balance(account.getCurrent_balance() + transaction_amount2);
		
		//grava novo valor de current_balance no banco
		if(account.newCurrentBalance(account)) {
			return true;
		}
		
		//grava os dados do deposito (transacao) no banco
		if(transaction.newTransaction(transaction)) {
			return true;
		}else {
			return false;
		}	
	}
}
