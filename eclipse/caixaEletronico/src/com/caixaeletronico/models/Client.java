package com.caixaeletronico.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity //indica que objetos dessa classe se tornem "persistível" no banco de dados, define a classe como uma tabela do banco
@Table(name="clients") //define o nome da tabela. quando não definido assume-se o nome da classe como nome da tabela
public class Client {
	
	/* ----------atributos---------- */

	@Id //define o id (PK) da tabela
	private String owner_cpf;
	private String owner_name;
	
	//indica o relacionamento um-para-muitos: targetEntity: a qual entidade está se associando
	@OneToMany(mappedBy = "client", targetEntity = Account.class, cascade = CascadeType.ALL)
	private List<Account> accounts;
	
	/* ----------construtores---------- */	
	
	public Client() {

	}
	
	public Client(String owner_cpf) {
		super();
		this.owner_cpf = owner_cpf;
	}
	
	public Client(String owner_cpf, String owner_name) {
		super();
		this.owner_cpf = owner_cpf;
		this.owner_name = owner_name;
	}
	
	/* ----------getters/setters---------- */
		
	public String getOwner_cpf() {
		return owner_cpf;
	}

	public void setOwner_cpf(String owner_cpf) {
		this.owner_cpf = owner_cpf;
	}

	public String getOwner_name() {
		return owner_name;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
		
	/* ----------metodos---------- */

}
