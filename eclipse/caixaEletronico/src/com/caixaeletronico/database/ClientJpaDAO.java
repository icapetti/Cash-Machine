package com.caixaeletronico.database;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.caixaeletronico.models.Client;

public class ClientJpaDAO {
	/*
	 * As classes DAO estão usando o padrão de projeto Singleton PARA que apenas uma instância 
	 * da classe seja criada durante toda a aplicação. Ao realizar a criação da classe pela primeira 
	 * vez o método getEntityManager() é chamado, responsável por criar uma instância de EntityManager.
	*/
	
	/* ---------- INICIO ----------*/
	
	/* ----------ATRIBUTOS----------*/
	
	private static ClientJpaDAO instance;
    protected static EntityManager entityManager;
     
    public static ClientJpaDAO getInstance(){
      if (instance == null){
         instance = new ClientJpaDAO();
      }
       
      return instance;
    }

    private ClientJpaDAO() {
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
    
    public Client getById(final int id) {
        return entityManager.find(Client.class, id);
     }

    @SuppressWarnings("unchecked")
    public List<Client> findAll() {
    	return entityManager.createQuery("FROM " + 
        Client.class.getName()).getResultList();
     }

     /* metodo persist(): inicia uma transação através do getTransaction(). begin() e finaliza a transação no 
      * commit(), dentro dessa transação é executado o método persist() que salva o objeto (account). 
      * em caso de erro um rollback() é executado e todas as alterações são desfeitas
      * função apenas de inserir no banco
     */
      
     public void persist(Client client) {
       try {
          entityManager.getTransaction().begin();
          entityManager.persist(client);
          entityManager.getTransaction().commit();
       } catch (Exception ex) {
          ex.printStackTrace();
          entityManager.getTransaction().rollback();
       }
     }

     /*mesmo principio do metodo persist(), porem tenta primeiro atualizar o registro. se ainda não houver registro 
      * ele também insere
     */
      
     public void merge(Client client) {
       try {
          entityManager.getTransaction().begin();
          entityManager.merge(client);
          entityManager.getTransaction().commit();
       } catch (Exception ex) {
          ex.printStackTrace();
          entityManager.getTransaction().rollback();
       }
     }

     /*procura o objeto (account) no banco e então remover*/
      
     public void remove(Client client) {
       try {
          entityManager.getTransaction().begin();
          client = entityManager.find(Client.class, client.getOwner_cpf());
          entityManager.remove(client);
          entityManager.getTransaction().commit();
       } catch (Exception ex) {
          ex.printStackTrace();
          entityManager.getTransaction().rollback();
       }
     }

     public void removeById(final int id) {
       try {
       	Client client = getById(id);
          remove(client);
       } catch (Exception ex) {
          ex.printStackTrace();
       }
     }
}
