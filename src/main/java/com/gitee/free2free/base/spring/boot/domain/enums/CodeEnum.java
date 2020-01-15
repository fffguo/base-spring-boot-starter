package com.gitee.free2free.base.spring.boot.domain.enums;

/**
 * @author lfg
 * @version 1.0
 */
public enum CodeEnum {

    /**
     * 参数
     */
    SUCCESS("0", "请求成功"),
    FAIL("1", "请求失败"),
    SERVICE_ERROR("500", "服务器内部错误"),
    PARAM_MISS("1001", "参数缺失"),
    PARAM_NO_VALID("1002", "参数校验不通过"),
    ;

    private String code;
    private String msg;

    CodeEnum(String code, String msg) {
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
