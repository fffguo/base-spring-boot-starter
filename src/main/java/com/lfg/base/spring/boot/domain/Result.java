package com.lfg.base.spring.boot.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lfg.base.spring.boot.domain.enums.ResultCodeEnum;
import lombok.Data;

/**
 * @author lfg
 * @version 1.0
 */
@Data
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
}
