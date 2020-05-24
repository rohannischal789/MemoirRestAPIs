package restMovie;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import restMovie.Credential;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-16T23:48:42")
@StaticMetamodel(Person.class)
public class Person_ { 

    public static volatile SingularAttribute<Person, String> firstname;
    public static volatile SingularAttribute<Person, String> address;
    public static volatile SingularAttribute<Person, String> gender;
    public static volatile SingularAttribute<Person, String> surname;
    public static volatile SingularAttribute<Person, Date> dob;
    public static volatile CollectionAttribute<Person, Credential> credentialCollection;
    public static volatile SingularAttribute<Person, String> postcode;
    public static volatile SingularAttribute<Person, Integer> personid;
    public static volatile SingularAttribute<Person, String> state;

}