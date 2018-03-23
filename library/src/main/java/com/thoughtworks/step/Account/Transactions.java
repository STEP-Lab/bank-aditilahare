package com.thoughtworks.step.Account;
import java.util.ArrayList;

public class Transactions {

    protected ArrayList<Transaction> list;

    public Transactions() {
        this.list = new ArrayList<>();
    }

    public void debit(double amount, String name) {
        this.list.add(new DebitTransaction(amount,name));
    }
}
