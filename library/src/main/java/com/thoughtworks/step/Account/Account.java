package Account;

import MinimumBalanceException.MinimumBalanceException;

public class Account {
    private final String accountNumber;
    private int balance;

    public Account(String accountNumber, int balance)throws MinimumBalanceException{
        this.accountNumber = accountNumber;
        if(balance<1000) {
            throw new MinimumBalanceException();
        }
        this.balance = balance;
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
        balance -= amount;
        if(balance<1000){
            throw new MinimumBalanceException();
        }
        return balance;
    }
}
