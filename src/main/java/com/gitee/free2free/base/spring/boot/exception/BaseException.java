package com.gitee.free2free.base.spring.boot.exception;

import com.gitee.free2free.base.spring.boot.controller.CodeConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lfg
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {

    private String code;

    public BaseException(String message) {
        super(message);
        this.code = CodeConstant.FAIL;
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = String.valueOf(code);
    }

}
