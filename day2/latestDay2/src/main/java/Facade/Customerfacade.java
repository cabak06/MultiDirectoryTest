
package Facade;

import entity.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class Customerfacade {
 
    
    private static EntityManagerFactory emf;
    private static Customerfacade instance;

    public Customerfacade() {}
    
       public static Customerfacade getCustomerfacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new Customerfacade();
        }
        return instance;
    }


  public Customer findByID(long id){
          
         EntityManager em = emf.createEntityManager();
        try{
            Customer cust = em.find(Customer.class,id);
            return cust;
        }finally {
            em.close();
        }
    }

  public List<Customer> findByLastName(String lastName){
       EntityManager em = emf.createEntityManager();

        try{
        TypedQuery<Customer> query = em.createQuery("Select c from Customer c Where c.lastName = :lastName", Customer.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
        } finally
        {
            em.close();
        }
  }
  
 public int getNumberOfCustomers(){
     
     EntityManager em = emf.createEntityManager(); 
     
     try{
            TypedQuery<Customer> query = 
                       em.createQuery("Select c from Customer c",Customer.class);
            List<Customer> list =  query.getResultList();
            return list.size();
        }finally {
            em.close();
        }
     
 }
  
 
 public List<Customer> allCustomers(){
      EntityManager em = emf.createEntityManager(); 
      
       try{
            TypedQuery<Customer> query = 
                       em.createQuery("Select c from Customer c",Customer.class);
                        return query.getResultList();
 }finally{
           em.close();
       }
 }
 
 public Customer addCustomer(String fName, String lName){
     
     EntityManager em = emf.createEntityManager();
     Customer customer = new Customer("New","Customer");
     
     try{
      em.getTransaction().begin();
      em.persist(customer);
      em.getTransaction().commit();
       return customer;  
     }finally{
      em.close();
     }
     
 }
 
 
 
   
    public static void main(String[] args) {
        
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");      
    Customerfacade facade = Customerfacade.getCustomerfacade(emf);

    
         System.out.println("Cusotmer By Id: "+ facade.findByID(1) );
         System.out.println("find by lastname: " + facade.findByLastName("Darion"));
         System.out.println("Number of customers: "+facade.getNumberOfCustomers());
         System.out.println("All Customers: " + facade.allCustomers());
         System.out.println("New Customer added: "+facade.addCustomer("New","Customer"));
         
    }
    
    
}
