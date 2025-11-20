package com.example.demo.model;

public enum ReturnCodeAndDescEnum {

    SUCCESS("0000", "交易成功"),
    INVALID_INPUT("E001", "必填欄位不完整"),
    UPDATE_FAIL("E002", "更新失敗"),
    INSERT_FAIL("E003", "新增失敗"),
    DELETE_FAIL("E004", "刪除失敗"),
    DATA_NOT_FOUND("E005", "查無資料"),
    INVALID_TIME("E006", "時機不對"),
    S9999("9999", "其他系統異常");

    private String code;

    private String desc;

    private ReturnCodeAndDescEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}