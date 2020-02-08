
package rest;

import Facade.Customerfacade;
import com.google.gson.Gson;
import entity.Customer;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


@Path("customer")
public class CustomerResource {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    Customerfacade facade = Customerfacade.getCustomerfacade(emf);
    
    
    
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CustomerResource
     */
    public CustomerResource() {
    }

    /**
     * Retrieves representation of an instance of rest.CustomerResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
     return "Hello to the customer web test class";
    }

    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCustomers(){
      
    List<Customer> full = facade.allCustomers();
    return new Gson().toJson(full);
    }
    
     @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
     public String getRandomCustomer(){
    
    Random random = new Random();     
    List<Customer> full = facade.allCustomers();
    int number = random.nextInt(full.size()-1);
    
    return new Gson().toJson(full.get(number));
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    
    public String findCustomerById(@PathParam("id") int id) {
       Customer s = facade.findByID(id);
       
       return new Gson().toJson(s);
        
    } 
     
    
    /**
     * PUT method for updating or creating an instance of CustomerResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
