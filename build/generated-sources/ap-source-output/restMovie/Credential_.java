package restMovie;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import restMovie.Memoir;
import restMovie.Person;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-16T23:48:42")
@StaticMetamodel(Credential.class)
public class Credential_ { 

    public static volatile SingularAttribute<Credential, Date> signupdate;
    public static volatile CollectionAttribute<Credential, Memoir> memoirCollection;
    public static volatile SingularAttribute<Credential, Integer> credentialid;
    public static volatile SingularAttribute<Credential, Person> personid;
    public static volatile SingularAttribute<Credential, String> passwordhash;
    public static volatile SingularAttribute<Credential, String> username;

}