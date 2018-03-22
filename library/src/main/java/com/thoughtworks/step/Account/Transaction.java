package com.thoughtworks.step.Account;
import java.util.Date;

public abstract class Transaction {
    protected Date date;
    protected final double amount;
    protected final String to;

    public Transaction(Date date, double amount, String to) {
        this.date = date;
        this.amount = amount;
        this.to = to;
    }

    public Date getDate() {
        return date;
    }

}
