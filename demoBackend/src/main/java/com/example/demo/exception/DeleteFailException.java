package com.example.demo.exception;

/**
 * 資料刪除異常
 */
public class DeleteFailException extends Exception {

    public DeleteFailException() {
    }

    public DeleteFailException(String message) {
        super(message);
    }
}