package com.thoughtworks.step.Account;

import java.util.Date;

public class DebitTransaction extends Transaction {

    String type = "Debit";

    protected DebitTransaction(Date date, double amount, String to) {
        super(date,amount,to);
    }

    public DebitTransaction(double amount,String name) {
        this(new Date(),amount,name);
    }

    public String getType(){
        return type;
    }
}
