package com.example.demo.exception;

/**
 * 資料重複異常
 */
public class DataRepeatedException extends Exception {

    public DataRepeatedException() {
    }

    public DataRepeatedException(String message) {
        super(message);
    }
}