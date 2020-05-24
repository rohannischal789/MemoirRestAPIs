/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restMovie;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rohan Nischal
 */
@Entity
@Table(name = "MEMOIR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Memoir.findAll", query = "SELECT m FROM Memoir m")
    , @NamedQuery(name = "Memoir.findByMemoirid", query = "SELECT m FROM Memoir m WHERE m.memoirid = :memoirid")
    , @NamedQuery(name = "Memoir.findByMoviename", query = "SELECT m FROM Memoir m WHERE UPPER(m.moviename) = UPPER(:moviename)")
    , @NamedQuery(name = "Memoir.findByMoviereleasedate", query = "SELECT m FROM Memoir m WHERE m.moviereleasedate = :moviereleasedate")
    , @NamedQuery(name = "Memoir.findByWatchdatetime", query = "SELECT m FROM Memoir m WHERE m.watchdatetime = :watchdatetime")
    , @NamedQuery(name = "Memoir.findByComment", query = "SELECT m FROM Memoir m WHERE UPPER(m.comment) = UPPER(:comment)")
    , @NamedQuery(name = "Memoir.findByReleaseDateAndCinemaPostCode", query = "SELECT m FROM Memoir m WHERE m.moviereleasedate = :moviereleasedate and UPPER(m.cinemaid.postcode) = UPPER(:postcode)")
    , @NamedQuery(name = "Memoir.findByStarrating", query = "SELECT m FROM Memoir m WHERE m.starrating = :starrating")})
public class Memoir implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEMOIRID")
    private Integer memoirid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "MOVIENAME")
    private String moviename;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOVIERELEASEDATE")
    @Temporal(TemporalType.DATE)
    private Date moviereleasedate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WATCHDATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date watchdatetime;
    @Size(max = 500)
    @Column(name = "COMMENT")
    private String comment;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "STARRATING")
    private BigDecimal starrating;
    @JoinColumn(name = "CINEMAID", referencedColumnName = "CINEMAID")
    @ManyToOne(optional = false)
    private Cinema cinemaid;
    @JoinColumn(name = "CREDENTIALID", referencedColumnName = "CREDENTIALID")
    @ManyToOne(optional = false)
    private Credential credentialid;

    public Memoir() {
    }

    public Memoir(Integer memoirid) {
        this.memoirid = memoirid;
    }

    public Memoir(Integer memoirid, String moviename, Date moviereleasedate, Date watchdatetime, BigDecimal starrating) {
        this.memoirid = memoirid;
        this.moviename = moviename;
        this.moviereleasedate = moviereleasedate;
        this.watchdatetime = watchdatetime;
        this.starrating = starrating;
    }

    public Integer getMemoirid() {
        return memoirid;
    }

    public void setMemoirid(Integer memoirid) {
        this.memoirid = memoirid;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public Date getMoviereleasedate() {
        return moviereleasedate;
    }

    public void setMoviereleasedate(Date moviereleasedate) {
        this.moviereleasedate = moviereleasedate;
    }

    public Date getWatchdatetime() {
        return watchdatetime;
    }

    public void setWatchdatetime(Date watchdatetime) {
        this.watchdatetime = watchdatetime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getStarrating() {
        return starrating;
    }

    public void setStarrating(BigDecimal starrating) {
        this.starrating = starrating;
    }

    public Cinema getCinemaid() {
        return cinemaid;
    }

    public void setCinemaid(Cinema cinemaid) {
        this.cinemaid = cinemaid;
    }

    public Credential getCredentialid() {
        return credentialid;
    }

    public void setCredentialid(Credential credentialid) {
        this.credentialid = credentialid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memoirid != null ? memoirid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Memoir)) {
            return false;
        }
        Memoir other = (Memoir) object;
        if ((this.memoirid == null && other.memoirid != null) || (this.memoirid != null && !this.memoirid.equals(other.memoirid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restMovie.Memoir[ memoirid=" + memoirid + " ]";
    }
    
}
