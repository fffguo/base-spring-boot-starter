package com.gitee.free2free.base.spring.boot.controller;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author lfg
 * @version 1.0
 */
@Data
public class Result {

    /**
     * code
     */
    @JSONField(ordinal = 1)
    private String code;

    /**
     * message
     */
    @JSONField(ordinal = 2)
    private String msg;

    /**
     * data
     */
    @JSONField(ordinal = 3)
    private Object data;

    public Result() {
    }

    public Result(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
