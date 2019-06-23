package com.caixaeletronico.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.caixaeletronico.database.TransactionJpaDAO;

@Entity //indica que objetos dessa classe se tornem "persistível" no banco de dados, define a classe como uma tabela do banco
@Table(name="transactions") //define o nome da tabela, se não for definido assume-se o nome da classe como nome da tabela
public class Transaction {
	
	/* ----------atributos---------- */
	
	@Id //define id (PK) da tabela
	@GeneratedValue(strategy = GenerationType.IDENTITY) //define que a id (PK) seja populada pelo banco (auto increment)
	private int transaction_id;
	private String transaction_date;
	private Float transaction_amount;	
	private String transaction_type;
	private Float transaction_currenct_balance;
	
	@ManyToOne //indica o relacionamento muitos-para-um com a tabela/classe Account
	@JoinColumn(name="accounts_account_number") //FK
	private Account account;
	
	/* ----------construtores---------- */	
	
	public Transaction() {

	}
	
	public Transaction(String transaction_date, Float transaction_amount, String transaction_type,
		Float transaction_currenct_balance, Account account) {
		super();
		this.transaction_date = transaction_date;
		this.transaction_amount = transaction_amount;
		this.transaction_type = transaction_type;
		this.transaction_currenct_balance = transaction_currenct_balance;
		this.account = account;
	}
	
	public Transaction(int transaction_id, String transaction_date, Float transaction_amount, String transaction_type,
		Float transaction_currenct_balance, Account account) {
		super();
		this.transaction_id = transaction_id;
		this.transaction_date = transaction_date;
		this.transaction_amount = transaction_amount;
		this.transaction_type = transaction_type;
		this.transaction_currenct_balance = transaction_currenct_balance;
		this.account = account;
	}
	
	/* ----------getters/setters---------- */
	
	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(String date_now) {
		this.transaction_date = date_now;
	}

	public Float getTransaction_amount() {
		return transaction_amount;
	}

	public void setTransaction_amount(Float transaction_amount) {
		this.transaction_amount = transaction_amount;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public Float getTransaction_currenct_balance() {
		return transaction_currenct_balance;
	}

	public void setTransaction_currenct_balance(Float transaction_currenct_balance) {
		this.transaction_currenct_balance = transaction_currenct_balance;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
		
	/* ----------metodos---------- */
	
	//grava dos dados no banco
	public boolean newTransaction(Transaction transaction) {
		if(TransactionJpaDAO.persist(transaction)) {
			return true;
		}else {
			return false;
		}
	}
	
	@SuppressWarnings("unused")
	public boolean canWithdraw(Account account, float transaction_amount) {
		return account.getCurrent_balance() >= transaction_amount;
	}
}
