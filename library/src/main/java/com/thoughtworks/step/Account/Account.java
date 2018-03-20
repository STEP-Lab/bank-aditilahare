package Account;

import MinimumBalanceException.MinimumBalanceException;

public class Account {
    private final String accountNumber;
    private final int balance;

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
}
