package com.caixaeletronico.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import com.caixaeletronico.models.Account;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuAccountView {

	static JFrame frame;
	static DepositView enviaDados;
	static WithdrawView enviaDadosWithdraw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					MenuAccountView window = new MenuAccountView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public MenuAccountView() {
		initialize();
	}
	
	
	/*
	 * RECEBE DADOS DA VIEW AccessAccountView
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
		JLabel value_account_number = new JLabel(account_number);
		value_account_number.setBounds(53, 25, 117, 15);
		frame.getContentPane().add(value_account_number);
		
		String current_balance = String.valueOf(current_balance2);
		JLabel value_current_balance = new JLabel(current_balance);
		value_current_balance.setBounds(214, 25, 117, 15);
		frame.getContentPane().add(value_current_balance);
		
		JLabel value_owner_name = new JLabel(account_owner_name2);
		value_owner_name.setBounds(54, 68, 351, 15);
		frame.getContentPane().add(value_owner_name);	
		
		/*
		 * ACAO DOS BOTOES DEPOSIT E WITHDRAW: chamar view e enviar dados para a view
		 */
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				if (enviaDados == null) {
					enviaDados = new DepositView();
					enviaDados.frame.setVisible(true);
					enviaDados.dataFromView(account);
					frame.setVisible(false);
				}
			}
		});
		btnDeposit.setBounds(167, 108, 114, 25);
		frame.getContentPane().add(btnDeposit);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				if (enviaDadosWithdraw == null) {
					enviaDadosWithdraw = new WithdrawView();
					enviaDadosWithdraw.frame.setVisible(true);
					enviaDadosWithdraw.dataFromView(account);
					frame.setVisible(false);
				}
			}
		});
		btnWithdraw.setBounds(167, 145, 114, 25);
		frame.getContentPane().add(btnWithdraw);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public static void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_account_number = new JLabel("Account number");
		lbl_account_number.setBounds(54, 12, 130, 15);
		frame.getContentPane().add(lbl_account_number);
		
		JLabel lbl_owner = new JLabel("Owner");
		lbl_owner.setBounds(54, 52, 351, 15);
		frame.getContentPane().add(lbl_owner);
		
		JLabel lbl_current_balance = new JLabel("Current balance");
		lbl_current_balance.setBounds(214, 12, 117, 15);
		frame.getContentPane().add(lbl_current_balance);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(167, 182, 114, 25);
		frame.getContentPane().add(btnExit);
	}
}
