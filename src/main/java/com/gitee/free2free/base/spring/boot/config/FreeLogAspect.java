package com.gitee.free2free.base.spring.boot.config;

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
public class FreeLogAspect {
    /**
     * 请求日志格式
     */
    private String logFormatReq;
    /**
     * 返回日志格式
     */
    private String logFormatRes;


    public FreeLogAspect(String logFormatReq, String logFormatRes) {
        this.logFormatReq = logFormatReq;
        this.logFormatRes = logFormatRes;
    }

    @Around("@annotation(com.gitee.free2free.base.spring.boot.config.FreeLog)&&@annotation(freeLog)")
    public Object around(ProceedingJoinPoint pjp, FreeLog freeLog) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
        //tranceId
        String tranceId = "";
        if (logFormatReq.contains(FreeLogKeyConstant.TRANCE_ID) || logFormatRes.contains(FreeLogKeyConstant.TRANCE_ID)) {
            tranceId = UUID.randomUUID().toString().replace("-", "");
        }
        //请求路径
        String servletPath = freeLog.value();
        if (StringUtils.isEmpty(freeLog.value())) {
            servletPath = request.getServletPath();
        }
        //sessionId
        String sessionId = request.getSession().getId();

        //打印请求日志
        log.info(getReqLog(pjp, servletPath, tranceId, sessionId));

        //执行方法
        Object proceed = pjp.proceed();

        //打印返回日志
        log.info(getResLog(servletPath, tranceId, sessionId, proceed));
        return proceed;
    }

    /**
     * 获取请求日志
     */
    private String getReqLog(ProceedingJoinPoint pjp, String servletPath, String tranceId, String sessionId) {
        String reqLog = getCommonLog(servletPath, tranceId, sessionId, logFormatReq);
        if (logFormatReq.contains(FreeLogKeyConstant.REQUEST)) {
            reqLog = reqLog.replace(FreeLogKeyConstant.REQUEST, getRequestArgs(pjp));
        }
        return reqLog;
    }

    /**
     * 获取返回日志
     */
    private String getResLog(String servletPath, String tranceId, String sessionId, Object resObject) {
        String resLog = getCommonLog(servletPath, tranceId, sessionId, logFormatRes);
        if (logFormatRes.contains(FreeLogKeyConstant.RESPONSE)) {
            String resJson = "";
            if (resObject instanceof String) {
                resJson = resJson + resObject;
            } else {
                resJson = JSONObject.toJSONString(resObject);
            }
            resLog = resLog.replace(FreeLogKeyConstant.RESPONSE, resJson);
        }
        return resLog;
    }

    /**
     * 公共key
     */
    private String getCommonLog(String servletPath, String tranceId, String sessionId, String logFormat) {
        String log = logFormat;
        if (logFormat.contains(FreeLogKeyConstant.TRANCE_ID)) {
            log = log.replace(FreeLogKeyConstant.TRANCE_ID, tranceId);
        }
        if (logFormat.contains(FreeLogKeyConstant.SESSION_ID)) {
            log = log.replace(FreeLogKeyConstant.SESSION_ID, sessionId);
        }
        if (logFormat.contains(FreeLogKeyConstant.METHOD_NAME)) {
            log = log.replace(FreeLogKeyConstant.METHOD_NAME, servletPath);
        }
        return log;
    }

    /**
     * 获取请求参数
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
