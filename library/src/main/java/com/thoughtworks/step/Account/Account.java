package com.thoughtworks.step.Account;

public class Account {
    private final String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) throws MinimumBalanceException, InvalidAccountNumberException {
        checkForInvalidAccountNumber(accountNumber,"Invalid Account Number");
        this.accountNumber = accountNumber;
        checkMinimumBalance(balance, "Insufficient Balance");
        this.balance = balance;
    }

    private void checkForInvalidAccountNumber(String accountNumber,String message) throws InvalidAccountNumberException {
        if(!accountNumber.matches("\\d{4}-\\d{4}")){
            throw new InvalidAccountNumberException(message);
        }
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
        checkMinimumBalance(balance-amount, "Low balance : Can't process your withdraw request");
        balance -= amount;
        return balance;
    }
}