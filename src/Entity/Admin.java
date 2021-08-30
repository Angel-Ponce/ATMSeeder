package Entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author samyc
 */
public class Admin extends Person implements Serializable {

    public Admin(String name, String lastName, int age, String email, int pin, Date lastAccess, String pick) {
        super(name, lastName, age, email, pin, lastAccess, pick);
    }

    public Admin() {
    }

}
