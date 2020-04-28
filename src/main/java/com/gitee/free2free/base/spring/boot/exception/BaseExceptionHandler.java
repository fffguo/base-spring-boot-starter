package com.gitee.free2free.base.spring.boot.exception;

import cn.hutool.http.ContentType;
import com.alibaba.fastjson.JSONObject;
import com.gitee.free2free.base.spring.boot.controller.CodeConstant;
import com.gitee.free2free.base.spring.boot.controller.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

/**
 * @author lfg
 * @version 1.0
 */
@Slf4j
@ControllerAdvice
public class BaseExceptionHandler {


    /**
     * 处理 BaseException
     *
     * @param response response
     * @param e        异常
     */
    @ExceptionHandler(BaseException.class)
    public void handleServiceException(HttpServletResponse response, BaseException e) {
        log.error("BaseException:{}", e.getMessage(), e);
        print(response, JSONObject.toJSONString(new Result(e.getCode(), e.getMessage())));
    }


    /**
     * 处理 MissingServletRequestParameterException
     *
     * @param response response
     * @param e        异常
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public void handleMissingServletRequestParameterException(HttpServletResponse response, MissingServletRequestParameterException e) {
        log.error("MissingServletRequestParameterException:{}", e.getMessage(), e);
        print(response, JSONObject.toJSONString(new Result(CodeConstant.FAIL, "入参缺失：" + e.getMessage())));
    }

    /**
     * 处理 MethodArgumentNotValidException
     *
     * @param response response
     * @param e        异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleMethodArgumentNotValidException(HttpServletResponse response, MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException:{}", e.getMessage(), e);
        print(response, JSONObject.toJSONString(new Result(CodeConstant.FAIL, "入参校验不通过：" + e.getMessage())));
    }

    /**
     * 处理 Exception
     *
     * @param response response
     * @param e        异常
     */
    @ExceptionHandler(Exception.class)
    public void handleException(HttpServletResponse response, Exception e) {
        log.error("Exception:{}", e.getMessage(), e);
        print(response, JSONObject.toJSONString(new Result(CodeConstant.FAIL, "服务器内部错误：" + e.getMessage())));
    }

    /**
     * print
     *
     * @param response response
     * @param result   返回信息
     */
    protected void print(HttpServletResponse response, String result) {
        try {
            response.setContentType(ContentType.JSON.getValue());
            response.setStatus(HttpServletResponse.SC_OK);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(result);
        } catch (Exception e) {
            log.error("httpServletResponse print fail:{}", e.getMessage(), e);
        }

    }
}
