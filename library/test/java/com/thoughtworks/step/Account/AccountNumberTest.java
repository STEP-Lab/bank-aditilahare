package com.thoughtworks.step.Account;


import org.junit.Test;

public class AccountNumberTest {

    @Test(expected = InvalidAccountNumberException.class)
    public void checkValidAccountNumberWhileCreating() throws InvalidAccountNumberException {
        AccountNumber.createValidAccountNumber("abcd");
    }

    @Test(expected = InvalidAccountNumberException.class)
    public void checkForValidAccountNumber() throws InvalidAccountNumberException{
        AccountNumber.createValidAccountNumber("1234");
    }
    @Test
    public void accountNumberValidation() throws InvalidAccountNumberException{
        AccountNumber.createValidAccountNumber("1234-5678");
    }
}

