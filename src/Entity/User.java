package Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author samyc
 */
public class User extends Person implements Serializable {

    private int cardNumber;
    private int currentBalance;
    private int maximumAmount;
    private int countPinChanged = 0;
    ArrayList<Transaction> latestTransactions = new ArrayList<Transaction>();

    public User(int cardNumber, int currentBalance, int maximumAmount) {
        this.cardNumber = cardNumber;
        this.currentBalance = currentBalance;
        this.maximumAmount = maximumAmount;
    }

    public User(int cardNumber, int currentBalance, int maximumAmount, String name, String lastName, int age, String email, int pin, Date lastAccess, String pick) {
        super(name, lastName, age, email, pin, lastAccess, pick);
        this.cardNumber = cardNumber;
        this.currentBalance = currentBalance;
        this.maximumAmount = maximumAmount;
    }

    public User() {
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    public int getMaximumAmount() {
        return maximumAmount;
    }

    public int getMaximumRetreatPerDay() {
        int retreatsOnCurrentDay = (int) this.viewLatestTransactions().stream().filter((Transaction transaction) -> {
            //Filter all transactions with:
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());
            Calendar date = Calendar.getInstance();
            date.setTime(transaction.getDate());
            return now.get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH)
                    && now.get(Calendar.MONTH) == date.get(Calendar.MONTH)
                    && now.get(Calendar.YEAR) == date.get(Calendar.YEAR)
                    && transaction.getType().equals(Transaction.RETREAT);
        }).mapToDouble(transaction -> transaction.getAmount()).sum();
        return maximumAmount - retreatsOnCurrentDay;
    }

    public void setMaximumAmount(int maximumAmount) {
        this.maximumAmount = maximumAmount;
    }

    public void retreat(int mount) {
        this.currentBalance -= mount;
    }

    public void deposit(int mount) {
        this.currentBalance += mount;
    }

    public ArrayList<Transaction> viewLatestTransactions() {
        return this.latestTransactions;
    }

    public void addTransaction(Transaction transaction) {
        this.latestTransactions.add(transaction);
    }

    @Override
    public void setPin(int Pin) {
        super.setPin(Pin);
        this.countPinChanged++;
    }

    public int getCountPinChanged() {
        return countPinChanged;
    }

    public void setCountPinChanged(int countPinChanged) {
        this.countPinChanged = countPinChanged;
    }

    @Override
    public String toString() {
        return super.getName() + " " + super.getLastName();
    }

}
