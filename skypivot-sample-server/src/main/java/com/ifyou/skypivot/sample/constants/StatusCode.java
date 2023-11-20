package com.ifyou.skypivot.sample.constants;

public enum StatusCode {
    Success("0", "OK"),
    Fail("-1", "fail"),

    IdLessThanZeroError("10001","id不能小于等于0"),
    RecordNotExist("10002","记录不存在"),
    NOT_EDIT_LOCK("20005","没获取到编辑锁，不能保存");

    private String code;
    private String message;

    StatusCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
