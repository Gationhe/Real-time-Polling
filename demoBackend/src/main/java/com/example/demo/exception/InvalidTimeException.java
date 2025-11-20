package com.example.demo.exception;

/**
 * 時機不對異常
 */
public class InvalidTimeException extends Exception {

    public InvalidTimeException() {
    }

    public InvalidTimeException(String message) {
        super(message);
    }
}