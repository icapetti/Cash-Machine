package com.caixaeletronico.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.caixaeletronico.controllers.DepositController;
import com.caixaeletronico.models.Account;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DepositView {

	static JFrame frame;
	private static JTextField value_amount;
	static MenuAccountView voltaMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					DepositView window = new DepositView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DepositView() {
		initialize();
	}
	
	/*
	 * RECEBE DADOS DA VIEW MenuAccountView
	 */
	
	private static int account_number2;
	private static String account_owner_name2;
	private static float current_balance2;
	
	public static void dataFromView(Account account) {
		
		//pegando os dados de account
		account_number2 = account.getAccount_number();
		account_owner_name2 = account.getClient().getOwner_name();
		current_balance2 = account.getCurrent_balance();

		//mostrando os dados de account na tela
				
		String account_number =  String.valueOf(account_number2);
		JLabel lbl_value_account_number = new JLabel(account_number);
		lbl_value_account_number.setBounds(60, 27, 66, 15);
		frame.getContentPane().add(lbl_value_account_number);
				
		String current_balance = String.valueOf(current_balance2);
		JLabel lbl_value_current_balance = new JLabel(current_balance);
		lbl_value_current_balance.setBounds(225, 27, 124, 15);
		frame.getContentPane().add(lbl_value_current_balance);
		
		JLabel lbl_value_account_owner = new JLabel(account_owner_name2);
		lbl_value_account_owner.setBounds(60, 65, 328, 15);
		frame.getContentPane().add(lbl_value_account_owner);
		
		/*
		 * LABEL MENSAGEM DO RESULTADO DAS OPERACOES
		 */
		
		JLabel lbl_msg_result = new JLabel("");
		lbl_msg_result.setBounds(108, 78, 233, 15);
		frame.getContentPane().add(lbl_msg_result);
		
		/*
		 * ACAO BOTAO DEPOSIT
		 */
		
		value_amount = new JTextField();
		value_amount.setBounds(167, 132, 124, 19);
		frame.getContentPane().add(value_amount);
		value_amount.setColumns(10);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				String transaction_amount = value_amount.getText();
				if(DepositController.Deposit(account, transaction_amount)) {
					lbl_msg_result.setText("ERRO SISTEMICO. DEPOSITO NAO REALIZADO.");
				}else {
					lbl_msg_result.setText("DEPOSITO REALIZADO.");
				}
			}
		});
		btnDeposit.setBounds(167, 175, 124, 25);
		frame.getContentPane().add(btnDeposit);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				if (voltaMenu == null) {
					voltaMenu = new MenuAccountView();
					voltaMenu.frame.setVisible(true);
					voltaMenu.dataFromView(account);
					frame.setVisible(false);
				}
			}
		});
		btnBack.setBounds(168, 235, 114, 25);
		frame.getContentPane().add(btnBack);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_account_number = new JLabel("Account Number");
		lbl_account_number.setBounds(60, 12, 131, 15);
		frame.getContentPane().add(lbl_account_number);
		
		JLabel lbl_current_balance = new JLabel("Current balance");
		lbl_current_balance.setBounds(225, 12, 124, 15);
		frame.getContentPane().add(lbl_current_balance);
		
		JLabel lbl_account_owner_name = new JLabel("Owner");
		lbl_account_owner_name.setBounds(60, 52, 328, 15);
		frame.getContentPane().add(lbl_account_owner_name);
	}
}
