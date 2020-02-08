
package facades;

import employer.NewEmployer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class EmployerFacade {
 
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    
    public EntityManager getEntityManager(){
    return emf.createEntityManager();
    }
    
    public List<NewEmployer> getAllEmployees(){
     EntityManager em = getEntityManager();
     TypedQuery q = em.createQuery("SELECT n FROM NewEmployer n", NewEmployer.class);
     return q.getResultList();
    }
    
    public static void main(String[] args) {
        EmployerFacade ef = new EmployerFacade();
        EntityManager em = ef.getEntityManager();
        em.getTransaction().begin();
        em.persist(new NewEmployer ("Mario B", 20, 1000,"Copenhagen"));
        em.persist(new NewEmployer ("Cahit B", 40, 1001,"Manilla"));
        em.persist(new NewEmployer ("Enes den store ko", 11, 2000,"Ankara"));
        em.persist(new NewEmployer ("Abdullah den grimme abe", 13, 3000,"Regnskoven"));
        em.getTransaction().commit();
        ef.getAllEmployees().forEach((newEmployer)-> {System.out.println(newEmployer);});
        
    }
    
    
}
