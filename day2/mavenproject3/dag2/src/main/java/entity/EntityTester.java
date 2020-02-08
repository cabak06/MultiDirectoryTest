
package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EntityTester {
    
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        Book b1 = new Book("Author1");
        Book b2 = new Book("Author2");
        
        
        try{
            em.getTransaction().begin();
            em.persist(b1);
            em.persist(b2);
            em.getTransaction().commit();
            
            System.out.println(b1.getAuthor());
            System.out.println(b2.getAuthor());
            
            
        }finally{
            
            em.close();
        }
                
        
        
        
    }
    
    
}
