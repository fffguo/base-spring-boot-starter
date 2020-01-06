package com.gitee.free2free.base.spring.boot.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gitee.free2free.base.spring.boot.domain.enums.ResultCodeEnum;

/**
 * @author lfg
 * @version 1.0
 */
public class Result<T> {

    @JsonProperty(index = 1)
    private String code;
    @JsonProperty(index = 2)
    private String msg;
    @JsonProperty(index = 3)
    private T data;

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(ResultCodeEnum codeEnum, T data) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
