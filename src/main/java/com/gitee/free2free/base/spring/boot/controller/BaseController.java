package com.gitee.free2free.base.spring.boot.controller;

import com.gitee.free2free.base.spring.boot.domain.Result;
import com.gitee.free2free.base.spring.boot.domain.enums.ResultCodeEnum;

/**
 * @author lfg
 * @version 1.0
 */
public class BaseController {

    public static Result<Object> success() {
        return new Result<>(ResultCodeEnum.SUCCESS, null);
    }

    public static Result<Object> success(Object data) {
        return new Result<>(ResultCodeEnum.SUCCESS, data);
    }

    public static Result<Object> fail() {
        return new Result<>(ResultCodeEnum.FAIL, null);
    }

    public static Result<Object> fail(String msg) {
        return new Result<>(ResultCodeEnum.FAIL.getCode(), msg, null);
    }

    public static Result<Object> fail(String code, String msg) {
        return new Result<>(code, msg, null);
    }
}
