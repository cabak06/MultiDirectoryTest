
package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EntityTesting {
    
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        Customer c1 = new Customer("Mario","Marion");
        Customer c2 = new Customer("Sario","Sarion");
        Customer c3 = new Customer("Dario","Darion");
        
       try {
      em.getTransaction().begin();
      em.persist(c1);
      em.persist(c2);
      em.persist(c3);
      em.getTransaction().commit();
      //Verify that books are managed and has been given a database id
      System.out.println(c1.toString());
      System.out.println(c2.toString());
           System.out.println(c3.toString());
       	 
    	}finally{
        	em.close();
    	}
}

        
    }
    
   
