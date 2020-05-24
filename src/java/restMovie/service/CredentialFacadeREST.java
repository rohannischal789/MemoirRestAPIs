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
import restMovie.Credential;

/**
 *
 * @author Rohan Nischal
 */
@Stateless
@Path("restmovie.credential")
public class CredentialFacadeREST extends AbstractFacade<Credential> {

    @PersistenceContext(unitName = "MyMovieMemoirPU")
    private EntityManager em;

    public CredentialFacadeREST() {
        super(Credential.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Credential entity) {
        entity.setSignupdate(new Date());
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Credential entity) {
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
    public Credential find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("findByCredentialid/{Credentialid}")
    @Produces({"application/json"})
    public List<Credential> findByCredentialid(@PathParam("Credentialid") int credentialid) {
        Query query = em.createNamedQuery("Credential.findByCredentialid");
        query.setParameter("credentialid", credentialid);
        return query.getResultList();
    }
    
    @GET
    @Path("findByUsername/{Username}")
    @Produces({"application/json"})
    public List<Credential> findByUsername(@PathParam("Username") String username) {
        Query query = em.createNamedQuery("Credential.findByUsername");
        query.setParameter("username", username);
        return query.getResultList();
    }
    
    @GET
    @Path("checkIfUserExists/{Username}/{PasswordHash}")
    @Produces({"application/json"})
    public boolean checkIfUserExists(@PathParam("Username") String username, @PathParam("PasswordHash") String passwordhash) {
        Query query = em.createNamedQuery("Credential.findByUsernameAndPass");
        query.setParameter("username", username);
        query.setParameter("passwordhash", passwordhash);
        return (null!= query.getResultList() && query.getResultList().size() > 0);
    }
    
    @GET
    @Path("findByPasswordhash/{Passwordhash}")
    @Produces({"application/json"})
    public List<Credential> findByPasswordhash(@PathParam("Passwordhash") String passwordhash) {
        Query query = em.createNamedQuery("Credential.findByPasswordhash");
        query.setParameter("passwordhash", passwordhash);
        return query.getResultList();
    }
    
    @GET
    @Path("findBySignupdate/{Signupdate}")
    @Produces({"application/json"})
    public List<Credential> findBySignupdate(@PathParam("Signupdate") String signupdate) {
        Query query = em.createNamedQuery("Credential.findBySignupdate");
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(signupdate);
        } catch (ParseException ex) {
            Logger.getLogger(CredentialFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        query.setParameter("signupdate", date1);
        return query.getResultList();
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("getMaxCredentialID")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMaxCredentialID() {
        TypedQuery<Credential> q = em.createQuery("SELECT c FROM Credential c order by c.credentialid desc", Credential.class);
        int maxCredID = q.setMaxResults(1).getResultList().get(0).getCredentialid()+1;
        return String.valueOf(maxCredID);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
