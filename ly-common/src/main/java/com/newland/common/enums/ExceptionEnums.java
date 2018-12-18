package com.newland.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


public enum ExceptionEnums {
    PRICE_CANNOT_BE_NULL(400,"价格不能为空");

    private String message;
    private Integer code;

    ExceptionEnums( Integer code,String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
