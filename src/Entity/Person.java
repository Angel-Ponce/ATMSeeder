package Entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Angel Ponce
 */
public class Person implements Serializable {

    private String name;
    private String lastName;
    private int age;
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private String email;
    private int pin;
    private Date lastAccess;
    private String pick;

    public Person(String name, String lastName, int age, String email, int pin, Date lastAccess, String pick) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.pin = pin;
        this.lastAccess = lastAccess;
        this.pick = pick;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public String getPick() {
        return pick;
    }

    public void setPick(String pick) {
        this.pick = pick;
    }

    @Override
    public String toString() {
        return this.name + " " + this.lastName;
    }
}
