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
        if(!isValidAccountNumber(accountNumber)){
            throw new InvalidAccountNumberException(message);
        }
    }

    private boolean isValidAccountNumber(String accountNumber) {
        return accountNumber.matches("\\d{4}-\\d{4}");
    }

    private void checkMinimumBalance(double balance, String message) throws MinimumBalanceException {
        if (!canDebit(balance)) {
            throw new MinimumBalanceException(message);
        }
    }

    private boolean canDebit(double balance) {
        return balance > 1000;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double creditMoney(int amount) throws InvalidAmountException {
        checkValidAmount(amount,"Invalid amount : Can't process your credit request");
        balance += amount;
        return balance;
    }

    private void checkValidAmount(int amount,String message) throws InvalidAmountException {
        if(!canCredit(amount)){
            throw new InvalidAmountException(message);
        }
    }

    private boolean canCredit(int amount) {
        return amount > 0 ;
    }

    public double debitMoney(int amount) throws MinimumBalanceException {
        checkMinimumBalance(balance-amount, "Low balance : Can't process your withdraw request");
        balance -= amount;
        return balance;
    }
}
