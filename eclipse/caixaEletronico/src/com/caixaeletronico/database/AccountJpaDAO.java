package com.caixaeletronico.database;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import com.caixaeletronico.models.Account;

public class AccountJpaDAO {
	
	/*
	 * As classes DAO estão usando o padrão de projeto Singleton PARA que apenas uma instância 
	 * da classe seja criada durante toda a aplicação. Ao realizar a criação da classe pela primeira 
	 * vez o método getEntityManager() é chamado, responsável por criar uma instância de EntityManager.
	*/
	
	/* ---------- INICIO ----------*/
	
	/* ----------ATRIBUTOS----------*/
	
	private static AccountJpaDAO instance;
    protected static EntityManager entityManager;
     
    public static AccountJpaDAO getInstance(){
      if (instance == null){
         instance = new AccountJpaDAO();
      }
       
      return instance;
    }

    public AccountJpaDAO() {
      entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
    	
	    //usa as configurações do arquivo persistence.xml para criar uma instância de EntityManagerFactory
	     EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU"); 
	     
	     //verifica se entityManager nunca foi criado
	     if (entityManager == null) {
	    	
	    	 //createEntityManager() cria uma instância de EntityManager: responsável pelas operações de CRUD no bd
	    	 entityManager = factory.createEntityManager();
	     }
	
	     return entityManager;
    }
    
    /* ----------METODOS---------- */
    
    /* ---------- CONFERE CONTA E SENHA ---------- */

    public static Account getAccount(int account_number, String account_password) {
    	 
        try {
          Account account = (Account) entityManager
           .createQuery(
               "SELECT u from Account u where u.account_number = :account and u.account_password = :password")
           .setParameter("account", account_number)
           .setParameter("password", account_password).getSingleResult();
   
          return account;
        } catch (NoResultException e) {
              return null;
        }
      }
    
    public static Account getById(int id) {
        return entityManager.find(Account.class, id);
     }
    
    
    
    @SuppressWarnings("unchecked")
    public List<Account> findAll() {
    	return entityManager.createQuery("FROM " + Account.class.getName()).getResultList();
     }
    
    public static boolean persist(Account account) {
        try {
           entityManager.getTransaction().begin();
           entityManager.persist(account);
           entityManager.getTransaction().commit();
           return false;
        } catch (Exception ex) {
           ex.printStackTrace();
           entityManager.getTransaction().rollback();
           return true;
        }
      }

      public static boolean merge(Account account) {
        try {
           entityManager.getTransaction().begin();
           entityManager.merge(account);
           entityManager.getTransaction().commit();
           return false;
        } catch (Exception ex) {
           ex.printStackTrace();
           entityManager.getTransaction().rollback();
           return true;
        }
      }

      public void remove(Account account) {
        try {
           entityManager.getTransaction().begin();
           account = entityManager.find(Account.class, account.getAccount_number());
           entityManager.remove(account);
           entityManager.getTransaction().commit();
        } catch (Exception ex) {
           ex.printStackTrace();
           entityManager.getTransaction().rollback();
        }
      }

      public void removeById(final int id) {
        try {
           Account account = getById(id);
           remove(account);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
      }  
}
