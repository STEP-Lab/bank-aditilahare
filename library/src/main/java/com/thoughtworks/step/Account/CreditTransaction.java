package com.thoughtworks.step.Account;

import java.util.Date;

public class CreditTransaction extends Transaction{

    String type = "Credit";

    protected CreditTransaction(Date date, double amount, String name) {
        super(date,amount,name);
    }

    public CreditTransaction(double amount,String name) {
        this(new Date(),amount,name);
    }

    public String getType(){
        return type;
    }
}
