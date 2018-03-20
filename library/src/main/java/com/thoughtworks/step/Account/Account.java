package Account;

public class Account {
    private final String accountNumber;
    private final int balance;

    public Account(String accountNumber, int balance){
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
