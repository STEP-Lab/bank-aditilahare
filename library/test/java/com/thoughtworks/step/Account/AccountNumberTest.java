package com.thoughtworks.step.Account;


import org.junit.Test;

public class AccountNumberTest {

    @Test(expected = InvalidAccountNumberException.class)
    public void checkValidAccountNumberWhileCreating() throws InvalidAccountNumberException {
        AccountNumber accountNumber = new AccountNumber("1234");
    }
}

