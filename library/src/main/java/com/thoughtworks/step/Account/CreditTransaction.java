package com.thoughtworks.step.Account;

import java.util.Date;

public class CreditTransaction extends Transaction{

    protected CreditTransaction(Date date, double amount, String name) {
        super(date,amount,name);
    }

    public CreditTransaction(double amount,String name) {
        this(new Date(),amount,name);
    }
}
