package com.thoughtworks.step.Account;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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

    public Transactions filterByAmountLesserThan(double amount) {
        Transactions transactions = new Transactions();
        for (Transaction transaction:list){
            if(transaction.getAmount() <= amount){
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }

    public void print(PrintWriter writer) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter printWrite = new PrintWriter("sample.txt", "UTF-8");
        for (Transaction transaction:list){
            writer.println(transaction.toString());
            printWrite.println(transaction.toString());
        }
        printWrite.close();
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
}
