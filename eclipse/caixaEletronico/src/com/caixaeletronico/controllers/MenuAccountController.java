package com.caixaeletronico.controllers;

import com.caixaeletronico.database.AccountJpaDAO;
import com.caixaeletronico.models.Account;

public class MenuAccountController {
	public static void MenuAccount(int account_number, String account_password) {

		/*BUSCA OS DADOS DA CONTA*/
		Account account = AccountJpaDAO.getById(account_number);
		
		/*EXIBE OS DADOS DA CONTA*/
		System.out.print("MY ACCOUNT: " + account.getAccount_number() + "\n");
		System.out.print("OWNER: " + account.getClient().getOwner_name() + "\n");
		System.out.print("CURRENT BALANCE: " + account.getCurrent_balance() + "\n");
		/*
		Scanner sc = new Scanner(System.in);
    
		System.out.print("Selecione a opção desejada: 1.Deposit 2.Withdraw 3.Exit ");
		int opt = sc.nextInt();
		
		if(opt == 1) {
			System.out.print("DEPOSIT SELECIONADO");
			DepositController.Deposit(account);	
		}else if(opt == 2) {
			System.out.print("WITHDRAW SELECIONADO");
			WithdrawController.Withdraw(account);
		}else {
			System.out.print("EXIT SELECIONADO");
			System.exit(0);
		}
		
		sc.close();*/
	}
}
