package com.newland.common.vo;

import com.newland.common.enums.ExceptionEnums;

public class ExceptionResult {

    private int statue;
    private String message;
    private Long timestrap;

    public ExceptionResult(ExceptionEnums enums){
        this.statue = enums.getCode();
        this.message = enums.getMessage();
        this.timestrap=System.currentTimeMillis();
    }

    public int getStatue() {
        return statue;
    }

    public void setStatue(int statue) {
        this.statue = statue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestrap() {
        return timestrap;
    }

    public void setTimestrap(Long timestrap) {
        this.timestrap = timestrap;
    }
}
