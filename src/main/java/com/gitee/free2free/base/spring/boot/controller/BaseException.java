package com.gitee.free2free.base.spring.boot.controller;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lfg
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {

    private Integer code;

    public BaseException(String message) {
        super(message);
        this.code = CodeConstant.FAIL;
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}
