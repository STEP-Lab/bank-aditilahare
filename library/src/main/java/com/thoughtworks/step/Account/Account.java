package com.thoughtworks.step.Account;

public class Account {
    private final String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) throws MinimumBalanceException {
        this.accountNumber = accountNumber;
        checkMinimumBalance(balance, "Insufficient Balance");
        this.balance = balance;
    }

    private void checkMinimumBalance(double balance, String message) throws MinimumBalanceException {
        if (balance < 1000) {
            throw new MinimumBalanceException(message);
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double creditMoney(int amount) {
        balance += amount;
        return balance;
    }

    public double debitMoney(int amount) throws MinimumBalanceException {
        checkMinimumBalance(balance, "Low balance : Can't process your withdraw request");
        balance -= amount;
        return balance;
    }
}