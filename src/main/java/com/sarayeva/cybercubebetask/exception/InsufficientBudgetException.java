package com.sarayeva.cybercubebetask.exception;

public class InsufficientBudgetException extends RuntimeException{
    public InsufficientBudgetException(){
        super("Your budget is insufficient for creating analysis");
    }
}
