package com.caixaeletronico.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.caixaeletronico.models.Account;
import com.caixaeletronico.models.Transaction;

public class WithdrawController {
	public static int Withdraw(Account account, String transaction_amount) {
		
		/*inicializacoes*/
		Transaction transaction = new Transaction();
		Date date_now = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String transaction_date = dateFormat.format(date_now);
        Float transaction_amount2 = Float.valueOf(transaction_amount);
		
		if(transaction.canWithdraw(account, transaction_amount2)) {
			
			//define os dados da transaction
			transaction.setTransaction_date(transaction_date);
			transaction.setTransaction_amount(transaction_amount2);
			transaction.setTransaction_type("WITHDRAW");
			transaction.setTransaction_currenct_balance(account.getCurrent_balance() + (transaction_amount2*-1));
			transaction.setAccount(account);
			
			//define novo valor do current_balance do objeto account
			account.setCurrent_balance(account.getCurrent_balance() + (transaction_amount2*-1));
			
			//grava novo valor de current_balance no banco
			if(account.newCurrentBalance(account)) {
				return 1;
			}
			
			//grava os dados do saque (transacao) no banco
			if(transaction.newTransaction(transaction)) {
				return 1;
			}else {
				return 0;
			}
			
		}else {
			return 2;
		}
	}
}
