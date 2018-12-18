package com.newland.common.exception;

import com.newland.common.enums.ExceptionEnums;

public class LyException extends  RuntimeException {

    private ExceptionEnums enums;

    public ExceptionEnums getEnums() {
        return enums;
    }

    public void setEnums(ExceptionEnums enums) {
        this.enums = enums;
    }

    public LyException(ExceptionEnums enums) {
        this.enums = enums;
    }
}
