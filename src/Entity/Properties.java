package Entity;

import java.io.Serializable;

/**
 *
 * @author samyc
 */
public class Properties implements Serializable {

    private String theme;
    private int currentBalance;
    private Person lastPerson;
    private String date;

    public Properties(String theme, int currentBalance, Person lastPerson, String date) {
        this.theme = theme;
        this.currentBalance = currentBalance;
        this.lastPerson = lastPerson;
        this.date = date;
    }

    public Properties() {
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Person getLastPerson() {
        return lastPerson;
    }

    public void setLastPerson(Person lastPerson) {
        this.lastPerson = lastPerson;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
