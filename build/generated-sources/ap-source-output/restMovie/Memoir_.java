package restMovie;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import restMovie.Cinema;
import restMovie.Credential;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-16T23:48:42")
@StaticMetamodel(Memoir.class)
public class Memoir_ { 

    public static volatile SingularAttribute<Memoir, BigDecimal> starrating;
    public static volatile SingularAttribute<Memoir, Cinema> cinemaid;
    public static volatile SingularAttribute<Memoir, Date> moviereleasedate;
    public static volatile SingularAttribute<Memoir, Credential> credentialid;
    public static volatile SingularAttribute<Memoir, String> comment;
    public static volatile SingularAttribute<Memoir, Date> watchdatetime;
    public static volatile SingularAttribute<Memoir, Integer> memoirid;
    public static volatile SingularAttribute<Memoir, String> moviename;

}