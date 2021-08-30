package Entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author samyc
 */
public class Transaction implements Serializable {

    private int amount;
    private String type;
    private Date date;

    public static final String RETREAT = "RETREAT";
    public static final String DEPOSIT = "DEPOSIT";

    public Transaction(int amount, String type, Date date) {
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    public Transaction() {
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {

        return this.type + " " + this.date;

    }
}
