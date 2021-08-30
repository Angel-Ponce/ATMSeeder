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

    public Properties(String theme, int currentBalance, Person lastPerson) {
        this.theme = theme;
        this.currentBalance = currentBalance;
        this.lastPerson = lastPerson;
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

}
