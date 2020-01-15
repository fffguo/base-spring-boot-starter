package com.gitee.free2free.base.spring.boot.exception;

import com.gitee.free2free.base.spring.boot.domain.enums.CodeEnum;
import lombok.Data;

/**
 * 用于将异常信息通知给第三方平台
 *
 * @author lfg
 * @version 1.0
 */
@Data
public class NoticeException extends RuntimeException {

    private String code;

    public NoticeException(String message) {
        super(message);
        this.code = CodeEnum.FAIL.getCode();
    }

    public NoticeException(CodeEnum codeEnum) {
        super(codeEnum.getMsg());
        this.code = codeEnum.getCode();
    }

    public NoticeException(CodeEnum codeEnum, String message) {
        super(message);
        this.code = codeEnum.getCode();
    }

}
