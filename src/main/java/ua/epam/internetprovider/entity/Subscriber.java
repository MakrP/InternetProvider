package ua.epam.internetprovider.entity;

/**
 * Simple java bean that describes Subscriber in system
 * @author Marko Popyk
 * @version 1.0
 */
public class Subscriber {
    private long accountId;
    private String name;
    private String surname;
    private int balance;
    private String status;

    public Subscriber() {
    }

    public Subscriber(long accountId, String name, String surname, int balance, String status) {
        this.accountId = accountId;
        this.name = name;
        this.surname = surname;
        this.balance = balance;
        this.status = status;
    }

    public Subscriber(String name, String surname, int balance, String status) {
        this.name = name;
        this.surname = surname;
        this.balance = balance;
        this.status = status;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}




