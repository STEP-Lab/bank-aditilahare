package com.thoughtworks.step.Account;

public class AccountNumber {
    public AccountNumber(String number) throws InvalidAccountNumberException {
        validateAccountNumber(number);
    }

    private void validateAccountNumber(String number) throws InvalidAccountNumberException {
        if(!number.matches("\\d{4}-\\d{4}")){
            throw new InvalidAccountNumberException("Invalid Account Number");
        }
    }
}
