package com.example.demo.exception;

/**
 * 輸入格式異常
 */
public class InvalidInputException extends Exception {

    public InvalidInputException() {
    }

    public InvalidInputException(String message) {
        super(message);
    }
}