package com.gitee.free2free.base.spring.boot.controller;

/**
 * @author lfg
 * @version 1.0
 */
public class BaseController {

    public static Result success() {
        return new Result(CodeConstant.SUCCESS, "请求成功", null);
    }

    public static Result success(Object data) {
        return new Result(CodeConstant.SUCCESS, "请求成功", data);
    }

    public static Result fail() {
        return new Result(CodeConstant.FAIL, "请求失败");
    }

    public static Result fail(String msg) {
        return new Result(CodeConstant.FAIL, msg);
    }

    public static Result fail(String code, String msg) {
        return new Result(code, msg);
    }

    public static Result fail(Integer code, String msg) {
        return new Result(String.valueOf(code), msg);
    }
}
