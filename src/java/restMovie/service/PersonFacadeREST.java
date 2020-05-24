/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restMovie.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import restMovie.Person;

/**
 *
 * @author Rohan Nischal
 */
@Stateless
@Path("restmovie.person")
public class PersonFacadeREST extends AbstractFacade<Person> {

    @PersistenceContext(unitName = "MyMovieMemoirPU")
    private EntityManager em;

    public PersonFacadeREST() {
        super(Person.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Person entity) {
        entity.setGender(entity.getGender().equalsIgnoreCase("Male")? "M" : "F");
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Person entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Person find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("findByPersonid/{Personid}")
    @Produces({"application/json"})
        public List<Person> findByPersonid(@PathParam("Personid") int personid) {
        Query query = em.createNamedQuery("Person.findByPersonid");
        query.setParameter("personid", personid);
        return query.getResultList();
    }
    
    @GET
    @Path("findByFirstname/{Firstname}")
    @Produces({"application/json"})
        public List<Person> findByFirstname(@PathParam("Firstname") String firstname) {
        Query query = em.createNamedQuery("Person.findByFirstname");
        query.setParameter("firstname", firstname);
        return query.getResultList();
    }
        
    @GET
    @Path("findBySurname/{Surname}")
    @Produces({"application/json"})
        public List<Person> findBySurname(@PathParam("Surname") String surname) {
        Query query = em.createNamedQuery("Person.findBySurname");
        query.setParameter("surname", surname);
        return query.getResultList();
    }
        
    @GET
    @Path("findByGender/{Gender}")
    @Produces({"application/json"})
        public List<Person> findByGender(@PathParam("Gender") String gender) {
        Query query = em.createNamedQuery("Person.findByGender");
        query.setParameter("gender", gender);
        return query.getResultList();
    }
        
    @GET
    @Path("findByDob/{Dob}")
    @Produces({"application/json"})
        public List<Person> findByDob(@PathParam("Dob") String dob) {
        Query query = em.createNamedQuery("Person.findByDob");
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
        } catch (ParseException ex) {
            Logger.getLogger(CredentialFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        query.setParameter("dob", date1);
        return query.getResultList();
    }
        
    @GET
    @Path("findByAddress/{Address}")
    @Produces({"application/json"})
        public List<Person> findByAddress(@PathParam("Address") String address) {
        Query query = em.createNamedQuery("Person.findByAddress");
        query.setParameter("address", address);
        return query.getResultList();
    }
        
    @GET
    @Path("findByState/{State}")
    @Produces({"application/json"})
        public List<Person> findByState(@PathParam("State") String state) {
        Query query = em.createNamedQuery("Person.findByState");
        query.setParameter("state", state);
        return query.getResultList();
    }
        
    @GET
    @Path("findByPostcode/{Postcode}")
    @Produces({"application/json"})
        public List<Person> findByPostcode(@PathParam("Postcode") String postcode) {
        Query query = em.createNamedQuery("Person.findByPostcode");
        query.setParameter("postcode", postcode);
        return query.getResultList();
    }
        
    @GET
    @Path("findByFirstNameAndGenderAndPostCode/{FirstName}/{Gender}/{Postcode}")
    @Produces({"application/json"})
    public List<Person> findByFirstNameAndGenderAndPostCode(@PathParam("FirstName") String firstName,@PathParam("Gender") String gender,@PathParam("Postcode") String postcode) {
        TypedQuery<Person> q = em.createQuery("SELECT p FROM Person p WHERE UPPER(p.firstname) = UPPER(:firstname) and UPPER(p.gender) = UPPER(:gender) and UPPER(p.postcode) = UPPER(:postcode)", Person.class);
        q.setParameter("firstname", firstName);
        q.setParameter("gender", gender);
        q.setParameter("postcode", postcode);
        return q.getResultList();
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Person> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Person> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("getMaxPersonID")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMaxPersonID() {
        TypedQuery<Person> q = em.createQuery("SELECT p FROM Person p order by p.personid desc", Person.class);
        int maxPersonID = q.setMaxResults(1).getResultList().get(0).getPersonid() +1;
        return String.valueOf(maxPersonID);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
