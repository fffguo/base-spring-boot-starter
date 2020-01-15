package com.gitee.free2free.base.spring.boot.controller;

import com.gitee.free2free.base.spring.boot.domain.Result;
import com.gitee.free2free.base.spring.boot.domain.enums.CodeEnum;

/**
 * @author lfg
 * @version 1.0
 */
public class BaseController {

    public static Result<Object> success() {
        return new Result<>(CodeEnum.SUCCESS, null);
    }

    public static Result<Object> success(Object data) {
        return new Result<>(CodeEnum.SUCCESS, data);
    }

    public static Result<Object> fail() {
        return new Result<>(CodeEnum.FAIL, null);
    }

    public static Result<Object> fail(String msg) {
        return new Result<>(CodeEnum.FAIL.getCode(), msg, null);
    }

    public static Result<Object> fail(String code, String msg) {
        return new Result<>(code, msg, null);
    }

    public static Result<Object> fail(CodeEnum codeEnum) {
        return new Result<>(codeEnum, null);
    }

    public static Result<Object> fail(CodeEnum codeEnum, String msg) {
        return new Result<>(codeEnum.getCode(), msg, null);
    }
}
