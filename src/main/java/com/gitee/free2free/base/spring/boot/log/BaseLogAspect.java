package com.gitee.free2free.base.spring.boot.log;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StringUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponseWrapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @author lfg
 * @version 1.0
 */
@Aspect
@Slf4j
public class BaseLogAspect {
    /**
     * 请求日志格式
     */
    private final String LOG_FORMAT_REQUEST;
    /**
     * 返回日志格式
     */
    private final String LOG_FORMAT_RESPONSE;


    /**
     * 构造方法
     *
     * @param logFormatReq 请求日志格式
     * @param logFormatRes 返回日志格式
     */
    public BaseLogAspect(String logFormatReq, String logFormatRes) {
        this.LOG_FORMAT_REQUEST = logFormatReq;
        this.LOG_FORMAT_RESPONSE = logFormatRes;
    }

    /**
     * 日志切面
     *
     * @param pjp       切点
     * @param aroundLog 日志注解
     * @return controller返回结果
     * @throws Throwable controller执行异常
     */
    @Around("@annotation(com.gitee.free2free.base.spring.boot.log.AroundLog)&&@annotation(aroundLog)")
    public Object around(ProceedingJoinPoint pjp, AroundLog aroundLog) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
        //tranceId
        String tranceId = "";
        if (LOG_FORMAT_REQUEST.contains(BaseLogKeyConstant.TRANCE_ID) || LOG_FORMAT_RESPONSE.contains(BaseLogKeyConstant.TRANCE_ID)) {
            tranceId = UUID.randomUUID().toString().replace("-", "");
        }
        //请求路径
        String servletPath = aroundLog.value();
        if (StringUtils.isEmpty(aroundLog.value())) {
            servletPath = request.getServletPath();
        }
        //sessionId
        String sessionId = request.getSession().getId();

        //打印请求日志
        log.info(getReqLog(getRequestArgs(pjp), servletPath, tranceId, sessionId));

        //执行方法
        Object proceed = pjp.proceed();

        //打印返回日志
        log.info(getResLog(servletPath, tranceId, sessionId, proceed));
        return proceed;
    }

    /**
     * 获取请求日志
     *
     * @param servletPath 请求路径
     * @param tranceId    链路ID
     * @param sessionId   sessionId
     * @param reqParam    请求参数
     * @return 日志
     */
    private String getReqLog(String servletPath, String tranceId, String sessionId, String reqParam) {
        String reqLog = getCommonLog(servletPath, tranceId, sessionId, LOG_FORMAT_REQUEST);
        if (LOG_FORMAT_REQUEST.contains(BaseLogKeyConstant.REQUEST)) {
            reqLog = reqLog.replace(BaseLogKeyConstant.REQUEST, reqParam);
        }
        return reqLog;
    }

    /**
     * 获取返回日志
     *
     * @param servletPath 请求路径
     * @param tranceId    链路ID
     * @param sessionId   sessionId
     * @param resObject   程序返回结果
     * @return 返回日志
     */
    private String getResLog(String servletPath, String tranceId, String sessionId, Object resObject) {
        String resLog = getCommonLog(servletPath, tranceId, sessionId, LOG_FORMAT_RESPONSE);
        if (LOG_FORMAT_RESPONSE.contains(BaseLogKeyConstant.RESPONSE)) {
            String resJson = "";
            if (resObject instanceof String) {
                resJson = resJson + resObject;
            } else {
                resJson = JSONObject.toJSONString(resObject);
            }
            resLog = resLog.replace(BaseLogKeyConstant.RESPONSE, resJson);
        }
        return resLog;
    }

    /**
     * 获取通用日志
     *
     * @param servletPath 请求路劲
     * @param tranceId    链路ID
     * @param sessionId   sessionId
     * @param logFormat   日志格式
     * @return 日志
     */
    private String getCommonLog(String servletPath, String tranceId, String sessionId, String logFormat) {
        String log = logFormat;
        if (logFormat.contains(BaseLogKeyConstant.TRANCE_ID)) {
            log = log.replace(BaseLogKeyConstant.TRANCE_ID, tranceId);
        }
        if (logFormat.contains(BaseLogKeyConstant.SESSION_ID)) {
            log = log.replace(BaseLogKeyConstant.SESSION_ID, sessionId);
        }
        if (logFormat.contains(BaseLogKeyConstant.METHOD_NAME)) {
            log = log.replace(BaseLogKeyConstant.METHOD_NAME, servletPath);
        }
        return log;
    }

    /**
     * 获取请求参数
     *
     * @param joinPoint 切点
     * @return 请求参数
     */
    private String getRequestArgs(JoinPoint joinPoint) {
        try {
            // 参数名
            String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
            // 参数值
            Object[] objs = joinPoint.getArgs();
            Map<String, Object> paramMap = new HashMap<>(16);
            for (int i = 0; i < objs.length; i++) {
                if (objs[i] instanceof BeanPropertyBindingResult
                        || objs[i] instanceof ServletRequest
                        || objs[i] instanceof ServletResponse
                        || objs[i] instanceof MultipartFile) {
                    continue;
                }
                if (!(objs[i] instanceof ExtendedServletRequestDataBinder) && !(objs[i] instanceof HttpServletResponseWrapper)) {
                    paramMap.put(argNames[i], objs[i]);
                }
            }
            if (paramMap.size() > 0) {
                return JSONObject.toJSONString(paramMap);
            }
        } catch (Exception e) {
            log.error("请求参数转换异常:{}", e.getMessage(), e);
        }
        return "";
    }

}
