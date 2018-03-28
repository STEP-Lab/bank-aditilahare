package com.thoughtworks.step.Account;

public class Account {
    private final String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) throws MinimumBalanceException, InvalidAccountNumberException {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    private static void checkMinimumBalance(double balance, String message) throws MinimumBalanceException {
        if (!canDebit(balance)) {
            throw new MinimumBalanceException(message);
        }
    }

    public static Account checkForMinimumBalanceWhileCreatingAccount(String accountNumber,double balance) throws MinimumBalanceException, InvalidAccountNumberException {
        checkMinimumBalance(balance, "Insufficient Balance");
        return new Account(accountNumber,balance);
    }

    private static boolean canDebit(double balance) {
        return balance > 1000;
    }

    public double getBalance() {
        return balance;
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

    private static boolean canCredit(int amount) {
        return amount > 0 ;
    }

    public double debitMoney(int amount) throws MinimumBalanceException {
        checkMinimumBalance(balance-amount, "Low balance : Can't process your withdraw request");
        balance -= amount;
        return balance;
    }
}
