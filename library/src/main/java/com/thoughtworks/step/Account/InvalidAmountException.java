package com.thoughtworks.step.Account;

public class InvalidAmountException extends Throwable {
    InvalidAmountException(String message){
        super(message);
    }
}
