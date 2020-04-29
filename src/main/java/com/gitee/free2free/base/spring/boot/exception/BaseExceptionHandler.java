package com.gitee.free2free.base.spring.boot.exception;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import com.alibaba.fastjson.JSONObject;
import com.gitee.free2free.base.spring.boot.api.BaseApi;
import com.gitee.free2free.base.spring.boot.config.DingConfig;
import com.gitee.free2free.base.spring.boot.controller.CodeConstant;
import com.gitee.free2free.base.spring.boot.controller.Result;
import com.gitee.free2free.base.spring.boot.ding.DingException;
import com.gitee.free2free.base.spring.boot.ding.DingRequest;
import com.gitee.free2free.base.spring.boot.ding.DingResponse;
import com.gitee.free2free.base.spring.boot.ding.LinkDTO;
import com.gitee.free2free.base.spring.boot.utils.HttpServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @author lfg
 * @version 1.0
 */
@Slf4j
@ControllerAdvice
public class BaseExceptionHandler {

    private static final ExecutorService EXECUTE = newFixedThreadPool(3);
    @Autowired
    private DingConfig dingConfig;
    @Autowired
    private BaseApi baseApi;

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
     * 处理 DingException
     *
     * @param response response
     * @param e        异常
     */
    @ExceptionHandler(DingException.class)
    public void handleDingException(HttpServletResponse response, DingException e) {
        log.error("DingException:{}", e.getMessage(), e);
        print(response, JSONObject.toJSONString(new Result(CodeConstant.FAIL, e.getMessage())));
        //通知钉钉
        noticeDing(e);
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
        //通知钉钉
        noticeDing(e);
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

    /**
     * 通知钉钉
     *
     * @param e 异常
     */

    protected void noticeDing(Exception e) {
        if (StrUtil.isNotBlank(dingConfig.getWebHook())) {
            String sessionId = HttpServletUtils.getSessionId();
            EXECUTE.execute(() -> {
                try {
                    //异常堆栈字符串
                    StringWriter writer = new StringWriter();
                    e.printStackTrace(new PrintWriter(writer));
                    writer.close();
                    String detail = writer.toString();

                    //发起http请求
                    DingRequest request = new DingRequest();
                    request.setDingConfig(dingConfig);
                    request.setLink(
                            LinkDTO.builder()
                                    .title("告警: 服务器出现异常，请迅速排查问题")
                                    .text(detail.substring(0, detail.indexOf("at")))
                                    .messageUrl(dingConfig.getSkipUrl() + String.format("?sessionId=%s&msg=%s", sessionId, detail.replace("\r\n\t", "<br>")))
                                    .picUrl(dingConfig.getPictureUrl())
                                    .build()
                    );

                    DingResponse response = baseApi.execute(request);
                    if (!response.isSuccess()) {
                        log.error("异常告警通知钉钉失败！");
                    }
                } catch (Exception e1) {
                    log.error("异常告警通知钉钉出现异常：{}", e1.getMessage(), e1);
                }
            });

        }
    }
}
