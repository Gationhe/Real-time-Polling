package com.example.demo.controller.advice;

import com.example.demo.dto.Res;
import com.example.demo.dto.ResMwHeader;
import com.example.demo.exception.*;
import com.example.demo.model.ReturnCodeAndDescEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WebExceptionHandler {

    /**
     * 輸入格式異常處理
     */
    @ResponseBody
    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(HttpStatus.OK)
    public Res<Object> handleInvalidInputException(InvalidInputException ex) {
        ResMwHeader resMwHeader = new ResMwHeader();
        String message = ex.getMessage();
        if (message.isBlank()) {
            message = "輸入值不得為空";
        }
        resMwHeader.setReturnCode(ReturnCodeAndDescEnum.INVALID_INPUT.getCode());
        resMwHeader.setReturnDesc(message);
        return new Res<>(resMwHeader, null);
    }

    /**
     * 查無資料異常處理
     */
    @ResponseBody
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Res<Object> handleDataNotFoundException(DataNotFoundException ex) {
        return new Res<>(new ResMwHeader(ReturnCodeAndDescEnum.DATA_NOT_FOUND), null);
    }

    /**
     * 新增資料異常處理
     */
    @ResponseBody
    @ExceptionHandler(InsertFailException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Res<Object> handleInsertFailException(InsertFailException ex) {
        return new Res<>(new ResMwHeader(ReturnCodeAndDescEnum.INSERT_FAIL), null);
    }

    /**
     * 更新資料異常處理
     */
    @ResponseBody
    @ExceptionHandler(UpdateFailException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Res<Object> handleUpdateFailException(UpdateFailException ex) {
        return new Res<>(new ResMwHeader(ReturnCodeAndDescEnum.UPDATE_FAIL), null);
    }

    /**
     * 刪除資料異常處理
     */
    @ResponseBody
    @ExceptionHandler(DeleteFailException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Res<Object> handleDeleteFailException(DeleteFailException ex) {
        return new Res<>(new ResMwHeader(ReturnCodeAndDescEnum.DELETE_FAIL), null);
    }

    /**
     * 其他系統異常處理
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Res<Object> handleException(Exception ex) {
        return new Res<>(new ResMwHeader(ReturnCodeAndDescEnum.S9999), null);
    }
}


