package com.gitee.free2free.base.spring.boot.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author lfg
 * @version 1.0
 */
@Data
public class Result {

    @JsonProperty(index = 1)
    private Integer code;
    @JsonProperty(index = 2)
    private String msg;
    @JsonProperty(index = 3)
    private Object data;

    public Result() {
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
