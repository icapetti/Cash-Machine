package com.caixaeletronico.database;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.caixaeletronico.models.Transaction;

public class TransactionJpaDAO {
	/*
	 * As classes DAO estão usando o padrão de projeto Singleton PARA que apenas uma instância 
	 * da classe seja criada durante toda a aplicação. Ao realizar a criação da classe pela primeira 
	 * vez o método getEntityManager() é chamado, responsável por criar uma instância de EntityManager.
	*/
	
	/* ---------- INICIO ----------*/
	
	/* ----------ATRIBUTOS----------*/
	
	private static TransactionJpaDAO instance;
    protected static EntityManager entityManager;
     
    public static TransactionJpaDAO getInstance(){
      if (instance == null){
         instance = new TransactionJpaDAO();
      }
       
      return instance;
    }

    private static EntityManager getEntityManager() {
    	
    //usa as configurações do arquivo persistence.xml para criar uma instância de EntityManagerFactory
     EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU"); 
     
     //verifica se entityManager nunca foi criado
     if (entityManager == null) {
    	
    	 //createEntityManager() cria uma instância de EntityManager: responsável pelas operações de CRUD no bd
    	 entityManager = factory.createEntityManager();
     }
     return entityManager;
    }
    
    public TransactionJpaDAO() {
        entityManager = getEntityManager();
      }
    
    /* ----------METODOS---------- */
    
    public Transaction getById(final int id) {
        return entityManager.find(Transaction.class, id);
      }

    @SuppressWarnings("unchecked")
    public List<Transaction> findAll() {
    	return entityManager.createQuery("FROM " + 
        Transaction.class.getName()).getResultList();
     }

     /* metodo persist(): inicia uma transação através do getTransaction(). begin() e finaliza a transação no 
      * commit(), dentro dessa transação é executado o método persist() que salva o objeto (account). 
      * em caso de erro um rollback() é executado e todas as alterações são desfeitas
      * função apenas de inserir no banco
     */
    
     /* ----------GRAVAR TRANSACTION NO BANCO----------*/
     public static boolean persist(Transaction transaction) {
       try {
    	  getInstance(); 
    	  getEntityManager();
          entityManager.getTransaction().begin();
          entityManager.persist(transaction);
          entityManager.getTransaction().commit();
          return false;
       } catch (Exception ex) {
          ex.printStackTrace();
          entityManager.getTransaction().rollback();
          return true;
       }
     }

     /*mesmo principio do metodo persist(), porem tenta primeiro atualizar o registro. se ainda não houver registro 
      * ele também insere
     */
      
     public void merge(Transaction transaction) {
       try {
          entityManager.getTransaction().begin();
          entityManager.merge(transaction);
          entityManager.getTransaction().commit();
       } catch (Exception ex) {
          ex.printStackTrace();
          entityManager.getTransaction().rollback();
       }
     }

     /*procura o objeto (account) no banco e então remover*/
      
     public void remove(Transaction transaction) {
       try {
          entityManager.getTransaction().begin();
          transaction = entityManager.find(Transaction.class, transaction.getTransaction_id());
          entityManager.remove(transaction);
          entityManager.getTransaction().commit();
       } catch (Exception ex) {
          ex.printStackTrace();
          entityManager.getTransaction().rollback();
       }
     }

     public void removeById(final int id) {
       try {
       	Transaction transaction = getById(id);
          remove(transaction);
       } catch (Exception ex) {
          ex.printStackTrace();
       }
     }
}
