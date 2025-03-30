package com.test.springrestapi.exception;

public class MealNotFoundException extends Exception {
    public MealNotFoundException(String message) {
        super(message);
    }
}
