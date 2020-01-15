package com.gitee.free2free.base.spring.boot.exception;

import com.gitee.free2free.base.spring.boot.domain.enums.CodeEnum;
import lombok.Data;

/**
 * @author lfg
 * @version 1.0
 */
@Data
public class ServiceException extends RuntimeException {

    private String code;

    public ServiceException(String message) {
        super(message);
        this.code = CodeEnum.FAIL.getCode();
    }

    public ServiceException(CodeEnum codeEnum) {
        super(codeEnum.getMsg());
        this.code = codeEnum.getCode();
    }

    public ServiceException(CodeEnum codeEnum, String message) {
        super(message);
        this.code = codeEnum.getCode();
    }

}
