package com.gitee.free2free.base.spring.boot.ding;

import com.gitee.free2free.base.spring.boot.controller.BaseException;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用于将异常信息通知给第三方平台
 *
 * @author lfg
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NoticeException extends BaseException {

    public NoticeException(String message) {
        super(message);
    }

    public NoticeException(Integer code, String message) {
        super(code, message);
    }

    @Override
    public Integer getCode() {
        return super.getCode();
    }

    @Override
    public void setCode(Integer code) {
        super.setCode(code);
    }
}
