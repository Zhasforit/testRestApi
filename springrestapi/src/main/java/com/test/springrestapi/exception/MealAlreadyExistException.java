package com.test.springrestapi.exception;

public class MealAlreadyExistException extends Exception{
    public MealAlreadyExistException(String message) {
        super(message);
    }
}
