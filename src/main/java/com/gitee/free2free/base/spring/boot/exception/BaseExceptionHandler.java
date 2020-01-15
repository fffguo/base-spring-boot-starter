package com.gitee.free2free.base.spring.boot.exception;

import com.alibaba.fastjson.JSONObject;
import com.gitee.free2free.base.spring.boot.domain.Result;
import com.gitee.free2free.base.spring.boot.domain.enums.CodeEnum;
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
     * 处理 ServiceException
     *
     * @param response response
     * @param e        异常
     */
    @ExceptionHandler(ServiceException.class)
    public void handleServiceException(HttpServletResponse response, ServiceException e) {
        log.error("ServiceException:{}", e.getMessage(), e);
        print(response, JSONObject.toJSONString(new Result<>(e.getCode(), e.getMessage())));
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
        print(response, JSONObject.toJSONString(new Result<>(CodeEnum.PARAM_MISS.getCode(), "入参缺失：" + e.getMessage())));
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
        print(response, JSONObject.toJSONString(new Result<>(CodeEnum.PARAM_NO_VALID.getCode(), "入参校验不通过：" + e.getMessage())));
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
        print(response, JSONObject.toJSONString(new Result<>(CodeEnum.SERVICE_ERROR, null)));
    }

    /**
     * print
     *
     * @param response response
     * @param result   返回信息
     */
    private void print(HttpServletResponse response, String result) {
        try {
            response.setContentType("application/json; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(result);
        } catch (Exception e) {
            log.error("httpServletResponse print fail:{}", e.getMessage(), e);
        }

    }
}
