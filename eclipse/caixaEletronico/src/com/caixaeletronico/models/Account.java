package com.caixaeletronico.models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.caixaeletronico.database.AccountJpaDAO;

@Entity //indica que objetos dessa classe se tornem "persistível" no banco de dados, define a classe como uma tabela do banco
@Table(name="accounts")
public class Account {
	
	/* ----------atributos---------- */
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int account_number;
	private String account_password;
	private Float current_balance;
	private String account_status;
	
	//indica o relacionamento um-para-muitos: targetEntity: a qual entidade está se associando
	@OneToMany(mappedBy = "account", targetEntity = Transaction.class, cascade = CascadeType.ALL)
	private List<Transaction> transactions;
	
	//indica o relacionamento muitos-para-um com a tabela/classe Account
	@ManyToOne 
	@JoinColumn(name="clients_owner_cpf") //FK
	private Client client;
	
	/* ----------construtores---------- */	
	
	public Account(int account_number, String account_password, Float current_balance, String account_status,
			List<Transaction> transactions, Client client) {
		super();
		this.account_number = account_number;
		this.account_password = account_password;
		this.current_balance = current_balance;
		this.account_status = account_status;
		this.transactions = transactions;
		this.client = client;
	}
	
	public Account() {
		
	}
	
	/* ----------getters/setters---------- */



	public int getAccount_number() {
		return account_number;
	}

	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}

	public String getAccount_password() {
		return account_password;
	}

	public void setAccount_password(String account_password) {
		this.account_password = account_password;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Float getCurrent_balance() {
		return current_balance;
	}

	public void setCurrent_balance(Float current_balance) {
		this.current_balance = current_balance;
	}

	public String getAccount_status() {
		return account_status;
	}

	public void setAccount_status(String account_status) {
		this.account_status = account_status;
	}
	
	/* ----------metodos---------- */
	
	@SuppressWarnings("static-access")
	public static boolean accessAccount(int account_number, String account_password){
		AccountJpaDAO accountJpaDAO = new AccountJpaDAO();
		return accountJpaDAO.getAccount(account_number, account_password) != null;
	}
	
	//grava dos dados no banco
	public boolean newCurrentBalance(Account account) {
		if(AccountJpaDAO.merge(account)) {
			return true;
		}else {
			return false;
		}
	}
	
	public void viewTransactions(int account_number) {
		
	}
}
