package com.thoughtworks.step.Account;

public class Account {
    private final String accountNumber;
    private int balance;

    public Account(String accountNumber, int balance)throws MinimumBalanceException {
        this.accountNumber = accountNumber;
        checkMinimumBalance(balance, "Insufficient Balance");
        this.balance = balance;
    }

    private void checkMinimumBalance(int balance, String message) throws MinimumBalanceException {
        if (balance < 1000) {
            throw new MinimumBalanceException(message);
        }
    }

    public int getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int creditMoney(int amount) {
        balance += amount;
        return balance;
    }

    public int debitMoney(int amount) throws MinimumBalanceException{
        checkMinimumBalance(balance, "Low balance : Can't process your withdraw request");
        balance -= amount;
        return balance;
    }