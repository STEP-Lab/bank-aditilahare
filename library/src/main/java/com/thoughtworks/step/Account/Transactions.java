package com.thoughtworks.step.Account;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Transactions extends ArrayList<Transaction> {

    protected ArrayList<Transaction> list;

    public Transactions() {
        this.list = new ArrayList<>();
    }

    public void debit(double amount, String name) {
        this.list.add(new DebitTransaction(amount,name));
    }

    protected void debit(Date date,double amount, String name) {
        this.list.add(new DebitTransaction(date,amount,name));
    }

    public void credit(double amount, String name) {
        this.list.add(new CreditTransaction(amount,name));
    }

    protected void credit(Date date,double amount, String name) {
        this.list.add(new CreditTransaction(date,amount,name));
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

    public Transactions filterByAmountLesserThan(double amount) {
        Transactions transactions = new Transactions();
        for (Transaction transaction:list){
            if(transaction.getAmount() <= amount){
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }

    public void print(PrintWriter writer) {
        for (Transaction transaction:list){
            writer.println(transaction.toString());
        }
    }

    public Transactions filterAllDebitTransaction() {
        Transactions transactions = new Transactions();
        for (Transaction transaction:list){
            if (transaction instanceof DebitTransaction){
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }

    public Transactions filterAllCreditTransaction() {
        Transactions transactions = new Transactions();
        for (Transaction transaction :list){
            if (transaction instanceof CreditTransaction){
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }

    public void writeInCsv(FileWriter writer) throws IOException {
        for (Transaction transaction:list){
            writer.write(transaction.toCsv());
        }
    }

    public Transactions filterTransactionsBySpecificDate(Date date) {
        Transactions transactions = new Transactions();
        for (Transaction transaction:list){
            if(transaction.getDate().equals(date)){
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }

    public Transactions filterTransactionsBeforeSpecificDate(Date date) {
        Transactions transactions = new Transactions();
        for (Transaction transaction:list){
            if(transaction.getDate().before(date)){
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }

    public Transactions filterTransactionsAfterSpecificDate(Date date) {
        Transactions transactions = new Transactions();
        for (Transaction transaction:list){
            if(transaction.getDate().after(date)){
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }
}
