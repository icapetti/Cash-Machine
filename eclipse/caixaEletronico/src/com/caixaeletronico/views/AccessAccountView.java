package com.caixaeletronico.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import com.caixaeletronico.controllers.AccessAccountController;
import com.caixaeletronico.models.Account;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AccessAccountView {

	private static JFrame frame;
	private static JTextField get_value_account_number;
	private static JTextField get_value_password;
	static MenuAccountView enviaDados;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					AccessAccountView window = new AccessAccountView();
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
	public AccessAccountView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public static void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		get_value_account_number = new JTextField();
		get_value_account_number.setBounds(155, 70, 124, 19);
		frame.getContentPane().add(get_value_account_number);
		get_value_account_number.setColumns(10);
		
		JLabel lblNumeroDaConta = new JLabel("Account number");
		lblNumeroDaConta.setBounds(155, 43, 124, 15);
		frame.getContentPane().add(lblNumeroDaConta);
		
		get_value_password = new JTextField();
		get_value_password.setBounds(155, 128, 124, 19);
		frame.getContentPane().add(get_value_password);
		get_value_password.setColumns(10);
		
		JLabel lblSenha = new JLabel("Account password");
		lblSenha.setBounds(155, 101, 124, 15);
		frame.getContentPane().add(lblSenha);
		
		JLabel lbl_access_denied = new JLabel("");
		lbl_access_denied.setForeground(Color.RED);
		lbl_access_denied.setBounds(24, 206, 412, 15);
		frame.getContentPane().add(lbl_access_denied);
		
		JButton btnAccessAccount = new JButton("Access");
		btnAccessAccount.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				System.out.print("entrou nas acoes do botao: antes de conversoes");
				String convert = get_value_account_number.getText();	
				int account_number = Integer.parseInt(convert);
				System.out.print("valor de account_number ao passar pelo parseInt: " + account_number);
				String account_password = get_value_password.getText();
				
				if(AccessAccountController.AccessAccount(account_number, account_password) != null) {
					
					/*BUSCA OS DADOS DA CONTA*/
					Account account = AccessAccountController.AccessAccount(account_number, account_password);
					if (enviaDados == null) {
						enviaDados = new MenuAccountView();
						enviaDados.frame.setVisible(true);
						enviaDados.dataFromView(account);
						frame.setVisible(false);
					}
					
					//MenuAccountView.dataFromView(account);
				}else {
					lbl_access_denied.setText("Access denied: Password or account wrong!");
				}
			}
		});
		btnAccessAccount.setBounds(155, 169, 124, 25);
		frame.getContentPane().add(btnAccessAccount);
	}
}
