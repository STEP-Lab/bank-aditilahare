package com.thoughtworks.step.Account;
import java.util.ArrayList;

public class Transactions extends ArrayList<Transaction> {

    protected ArrayList<Transaction> list;

    public Transactions() {
        this.list = new ArrayList<>();
    }

    public void debit(double amount, String name) {
        this.list.add(new DebitTransaction(amount,name));
    }

    public void credit(double amount, String name) {
        this.list.add(new CreditTransaction(amount,name));
    }

    public Transactions filterByAmountGreaterThan(double amount) {
        Transactions transactions = new Transactions();
        for (Transaction transaction:list){
            if(transaction.getAmount() >= amount){
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }
}
