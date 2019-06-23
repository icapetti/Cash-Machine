package com.caixaeletronico.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.caixaeletronico.controllers.WithdrawController;
import com.caixaeletronico.models.Account;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WithdrawView {

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
					WithdrawView window = new WithdrawView();
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
	public WithdrawView() {
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
		lbl_value_account_number.setBounds(63, 25, 123, 15);
		frame.getContentPane().add(lbl_value_account_number);
				
		String current_balance = String.valueOf(current_balance2);
		JLabel lbl_value_current_balance = new JLabel(current_balance);
		lbl_value_current_balance.setBounds(256, 25, 123, 15);
		frame.getContentPane().add(lbl_value_current_balance);
		
		JLabel lbl_value_account_owner = new JLabel(account_owner_name2);
		lbl_value_account_owner.setBounds(63, 66, 262, 15);
		frame.getContentPane().add(lbl_value_account_owner);
		
		/*
		 * LABEL MENSAGEM DO RESULTADO DAS OPERACOES
		 */
		
		JLabel lbl_msg_result = new JLabel("");
		lbl_msg_result.setBounds(131, 93, 228, 15);
		frame.getContentPane().add(lbl_msg_result);
		
		/*
		 * ACAO BOTAO WITHDRAW
		 */
		
		value_amount = new JTextField();
		value_amount.setBounds(165, 147, 124, 19);
		frame.getContentPane().add(value_amount);
		value_amount.setColumns(10);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				String transaction_amount = value_amount.getText();
				int resultWithdraw = WithdrawController.Withdraw(account, transaction_amount);
				if(resultWithdraw == 1) {
					lbl_msg_result.setText("ERRO SISTEMICO. DEPOSITO NAO REALIZADO.");
				}else if (resultWithdraw == 2){
					lbl_msg_result.setText("SALDO INSUFICIENTE");
				}else if(resultWithdraw == 0) {
					lbl_msg_result.setText("SAQUE REALIZADO.");
				}
			}
		});
		btnWithdraw.setBounds(165, 176, 124, 25);
		frame.getContentPane().add(btnWithdraw);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				if (voltaMenu == null) {
					voltaMenu = new MenuAccountView();
					voltaMenu.frame.setVisible(true);
					voltaMenu.dataFromView(account);
					frame.setVisible(false);
				}
			}
		});
		btnBack.setBounds(175, 235, 114, 25);
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
		
		JLabel lbl_account_number = new JLabel("Account number");
		lbl_account_number.setBounds(63, 12, 123, 15);
		frame.getContentPane().add(lbl_account_number);
		
		JLabel lbl_current_balance = new JLabel("Currenct balance");
		lbl_current_balance.setBounds(256, 12, 123, 15);
		frame.getContentPane().add(lbl_current_balance);
		
		JLabel lbl_account_owner = new JLabel("Owner");
		lbl_account_owner.setBounds(63, 52, 262, 15);
		frame.getContentPane().add(lbl_account_owner);
		
		JLabel lbl_amount = new JLabel("Amount");
		lbl_amount.setBounds(189, 120, 66, 15);
		frame.getContentPane().add(lbl_amount);
	}
}
