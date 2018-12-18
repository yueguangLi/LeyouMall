package com.newland.common.advice;

import com.newland.common.enums.ExceptionEnums;
import com.newland.common.exception.LyException;
import com.newland.common.vo.ExceptionResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 默认情况下拦截controller的类
 */
@ControllerAdvice
public class CommonExceptionHandler {

    /**
     * 处理运行时异常
     * @return
     */
    @ExceptionHandler(LyException.class)
    public ResponseEntity<ExceptionResult> HandlerException(LyException e){
        ExceptionEnums enums = e.getEnums();
        return ResponseEntity.status(enums.getCode()).body(new ExceptionResult(enums));
    }
}
