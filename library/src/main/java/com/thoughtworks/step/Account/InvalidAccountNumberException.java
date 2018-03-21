package com.thoughtworks.step.Account;

public class InvalidAccountNumberException extends Throwable {
    InvalidAccountNumberException(String message){
        super(message);
    }
}
