package com.thoughtworks.step.Account;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

    private Account account;

    @Before
    public void setUp() throws MinimumBalanceException, InvalidAccountNumberException {
       account = new Account("1234-5678", 5000);
    }

    @Test
    public void checkBalance(){
        assertThat(account.getBalance(),is(5000.0));
    }


    @Test(expected= MinimumBalanceException.class)
    public void checkMinimumBalanceException() throws MinimumBalanceException,InvalidAccountNumberException{
        new Account("2345-6789",200);
    }

    @Test
    public void addMoneyIntoTheAccount () throws InvalidAmountException {
        account.creditMoney(1000);
        assertThat(account.getBalance(),is(6000.0));
    }

    @Test
    public void reduceMoneyFromAccount () throws MinimumBalanceException {
        account.debitMoney(1000);
        assertThat(account.getBalance(),is(4000.0));
    }

    @Test(expected = MinimumBalanceException.class)
    public void checkMinimumBalanceWhileDebiting() throws MinimumBalanceException,InvalidAccountNumberException{
        Account account = new Account("1234-5678", 3000);
        account.debitMoney(2800);
    }

    @Test(expected = InvalidAccountNumberException.class)
    public void checkValidAccountNumberWhileCreating() throws InvalidAccountNumberException, MinimumBalanceException {
        Account account = new Account("1234", 5000);
    }

    @Test (expected = InvalidAmountException.class)
    public void checkValidAmountWhileCrediting() throws InvalidAmountException, MinimumBalanceException, InvalidAccountNumberException {
        Account account = new Account("1234-5678", 2000);
        account.creditMoney(-10);
    }

}
