package com.thoughtworks.step.Account;

public class AccountNumber {
    private final String number;

    public AccountNumber(String number) throws InvalidAccountNumberException{
        this.number = number;
    }

    private static void validateAccountNumber(String number) throws InvalidAccountNumberException {
        if(!number.matches("\\d{4}-\\d{4}")){
            throw new InvalidAccountNumberException("Invalid Account Number");
        }
    }

    public static AccountNumber createValidAccountNumber(String number)throws InvalidAccountNumberException{
        validateAccountNumber(number);
        return new AccountNumber(number);
    }
}
