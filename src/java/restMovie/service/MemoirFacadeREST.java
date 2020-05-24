/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restMovie.service;

import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
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
import restMovie.Memoir;
import restMovie.Person;

/**
 *
 * @author Rohan Nischal
 */
@Stateless
@Path("restmovie.memoir")
public class MemoirFacadeREST extends AbstractFacade<Memoir> {

    @PersistenceContext(unitName = "MyMovieMemoirPU")
    private EntityManager em;

    public MemoirFacadeREST() {
        super(Memoir.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Memoir entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Memoir entity) {
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
    public Memoir find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("findByMemoirid/{Memoirid}")
    @Produces({"application/json"})
    public List<Memoir> findByMemoirid(@PathParam("Memoirid") int memoirid) {
        Query query = em.createNamedQuery("Memoir.findByMemoirid");
        query.setParameter("memoirid", memoirid);
        return query.getResultList();
    }

    @GET
    @Path("findByMoviename/{Moviename}")
    @Produces({"application/json"})
    public List<Memoir> findByMoviename(@PathParam("Moviename") String moviename) {
        Query query = em.createNamedQuery("Memoir.findByMoviename");
        query.setParameter("moviename", moviename);
        return query.getResultList();
    }

    @GET
    @Path("findByMoviereleasedate/{Moviereleasedate}")
    @Produces({"application/json"})
    public List<Memoir> findByMoviereleasedate(@PathParam("Moviereleasedate") String moviereleasedate) {
        Query query = em.createNamedQuery("Memoir.findByMoviereleasedate");
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(moviereleasedate);
        } catch (ParseException ex) {
            Logger.getLogger(CredentialFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        query.setParameter("moviereleasedate", date1);
        return query.getResultList();
    }

    @GET
    @Path("findByWatchdatetime/{Watchdatetime}")
    @Produces({"application/json"})
    public List<Memoir> findByWatchdatetime(@PathParam("Watchdatetime") String watchdatetime) {
        Query query = em.createNamedQuery("Memoir.findByWatchdatetime");
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(watchdatetime);
        } catch (ParseException ex) {
            Logger.getLogger(CredentialFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        query.setParameter("watchdatetime", date1);
        return query.getResultList();
    }

    @GET
    @Path("findByComment/{Comment}")
    @Produces({"application/json"})
    public List<Memoir> findByComment(@PathParam("Comment") String comment) {
        Query query = em.createNamedQuery("Memoir.findByComment");
        query.setParameter("comment", comment);
        return query.getResultList();
    }

    @GET
    @Path("findByStarrating/{Starrating}")
    @Produces({"application/json"})
    public List<Memoir> findByStarrating(@PathParam("Starrating") BigDecimal starrating) {
        Query query = em.createNamedQuery("Memoir.findByStarrating");
        query.setParameter("starrating", starrating);
        return query.getResultList();
    }

    @GET
    @Path("findbyMovieNameAndCinemaName/{MovieName}/{CinemaName}")
    @Produces({"application/json"})
    public List<Memoir> findbyMovieNameAndCinemaName(@PathParam("MovieName") String movieName, @PathParam("CinemaName") String cinemaName) {
        TypedQuery<Memoir> q = em.createQuery("SELECT m FROM Memoir m WHERE UPPER(m.moviename) = UPPER(:moviename) and UPPER(m.cinemaid.cinemaname) = UPPER(:cinemaname)", Memoir.class);
        q.setParameter("moviename", movieName);
        q.setParameter("cinemaname", cinemaName);
        return q.getResultList();
    }

    @GET
    @Path("findByReleaseDateAndCinemaPostCode/{MovieReleaseDate}/{Postcode}")
    @Produces({"application/json"})
    public List<Memoir> findByReleaseDateAndCinemaPostCode(@PathParam("MovieReleaseDate") String movieReleaseDate, @PathParam("Postcode") String postcode) {
        Query query = em.createNamedQuery("Memoir.findByReleaseDateAndCinemaPostCode");
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(movieReleaseDate);
        } catch (ParseException ex) {
            Logger.getLogger(CredentialFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        query.setParameter("moviereleasedate", date1);
        query.setParameter("postcode", postcode);
        return query.getResultList();
    }

    @GET
    @Path("findWatchedMovieCountByPostcode/{PersonID}/{StartDate}/{EndDate}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object findWatchedMovieCountByPostcode(@PathParam("PersonID") int personID, @PathParam("StartDate") String startDate, @PathParam("EndDate") String endDate) {
        Date parsedStartDate = null;
        Date parsedEndDate = null;
        try {
            parsedStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
            parsedEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        } catch (ParseException ex) {
            Logger.getLogger(CredentialFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        Query query = em.createQuery("select m.cinemaid.postcode,count(m) from Memoir m where m.credentialid.personid.personid = :personid and m.watchdatetime between :startdate and :enddate group by m.cinemaid.postcode", Object[].class) ;
        query.setParameter("personid", personID);
        query.setParameter("startdate", parsedStartDate);
        query.setParameter("enddate", parsedEndDate);
        List<Object[]> queryList = query.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Object[] row : queryList) {
            JsonObject personObject = Json.createObjectBuilder().
                    add("cinemaPostCode", (String) row[0])
                    .add("watchedMovieCount", (long) row[1]).build();
            arrayBuilder.add(personObject);
        }
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }
    
    @GET
    @Path("findWatchedMoviesPerMonth/{PersonID}/{Year}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object findWatchedMoviesPerMonth(@PathParam("PersonID") int personID, @PathParam("Year") int year) {
        Query query = em.createQuery("select FUNC('MONTH',m.watchdatetime),count(m) from Memoir m where m.credentialid.personid.personid = :personid and FUNC('YEAR',m.watchdatetime) =:year group by FUNC('MONTH',m.watchdatetime)", Object[].class) ;
        query.setParameter("personid", personID);
        query.setParameter("year", year);
        List<Object[]> queryList = query.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Object[] row : queryList) {
            JsonObject personObject = Json.createObjectBuilder().
                    add("watchMonth",(String) (new DateFormatSymbols().getMonths()[((Integer) row[0])-1] ))
                    .add("watchedMovieCount", (long) row[1]).build();
            arrayBuilder.add(personObject);
        }
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }
    
    @GET
    @Path("findHighestRatedMovies/{PersonID}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object findHighestRatedMovies(@PathParam("PersonID") int personID) {
        
        Query query = em.createQuery("select distinct m.moviename,m.starrating,m.moviereleasedate from Memoir m where m.credentialid.personid.personid = :personid and m.starrating = (select max(m1.starrating) from Memoir m1 where m.credentialid.personid.personid = :personid )", Object[].class) ;
        query.setParameter("personid", personID);
        List<Object[]> queryList = query.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Object[] row : queryList) {
            JsonObject personObject = Json.createObjectBuilder().
                    add("movieName",(String) row[0])
                    .add("starRating", (BigDecimal) row[1])
                    .add("movieReleaseDate",(String) (new SimpleDateFormat("yyyy-MM-dd").format((Date) row[2]))).build();
            arrayBuilder.add(personObject);
        }
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }
    
    @GET
    @Path("findWatchedMoviesWithSameReleaseYear/{PersonID}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object findWatchedMoviesWithSameReleaseYear(@PathParam("PersonID") int personID) {
        Query query = em.createQuery("Select m.moviename,FUNC('YEAR',m.moviereleasedate) from Memoir m where m.credentialid.personid.personid = :personid and FUNC('YEAR',m.moviereleasedate) = FUNC('YEAR',m.watchdatetime)" ,Object[].class) ;
        query.setParameter("personid", personID);
        List<Object[]> queryList = query.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Object[] row : queryList) {
            JsonObject personObject = Json.createObjectBuilder().
                    add("movieName",(String) row[0])
                    .add("movieReleaseYear",(int) row[1]).build();
            arrayBuilder.add(personObject);
        }
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }
    
    @GET
    @Path("findWatchedRemakeMovies/{PersonID}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object findWatchedRemakeMovies(@PathParam("PersonID") int personID) {
        Query query = em.createQuery("SELECT m.moviename,FUNC('YEAR',m.moviereleasedate) FROM Memoir m WHERE m.credentialid.personid.personid = :personid and m.moviename IN (select m1.moviename from Memoir m1 where m1.credentialid.personid.personid = :personid group by m1.moviename having count(DISTINCT m1.moviereleasedate) > 1)" ,Object[].class) ;
        query.setParameter("personid", personID);
        List<Object[]> queryList = query.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Object[] row : queryList) {
            JsonObject personObject = Json.createObjectBuilder().
                    add("movieName",(String) row[0])
                    .add("movieReleaseDate",(int) row[1]).build();
            arrayBuilder.add(personObject);
        }
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }
    
    @GET
    @Path("findTop5RecentYearHighestRatedMovies/{PersonID}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object findTop5RecentYearHighestRatedMovies(@PathParam("PersonID") int personID) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Query query = em.createQuery("select distinct m.moviename,m.starrating,m.moviereleasedate from Memoir m where m.credentialid.personid.personid = :personid and FUNC('YEAR',m.moviereleasedate) = :year order by m.starrating desc", Object[].class) ;
        query.setParameter("personid", personID);
        query.setParameter("year", year);
        List<Object[]> queryList = query.setMaxResults(5).getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Object[] row : queryList) {
            JsonObject personObject = Json.createObjectBuilder().
                    add("movieName",(String) row[0])
                    .add("starRating", (BigDecimal) row[1])
                    .add("movieReleaseDate",(String) (new SimpleDateFormat("yyyy-MM-dd").format((Date) row[2]))).build();
            arrayBuilder.add(personObject);
        }
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Memoir> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Memoir> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
