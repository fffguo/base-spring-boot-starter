package com.gitee.free2free.base.spring.boot.domain.enums;

/**
 * @author lfg
 * @version 1.0
 */
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

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
