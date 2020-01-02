package com.lfg.base.spring.boot.domain.enums;

import lombok.Getter;

/**
 * @author lfg
 * @version 1.0
 */
@Getter
public enum ResultCodeEnum {

    /**
     * 参数
     */
    SUCCESS("0", "请求成功"),
    FAIL("1", "请求失败"),
    ;

    private String code;
    private String msg;

    ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
