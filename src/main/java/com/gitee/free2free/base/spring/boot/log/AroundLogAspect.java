package com.gitee.free2free.base.spring.boot.log;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.util.HashMap;
import java.util.Map;

import static com.gitee.free2free.base.spring.boot.log.AroundFormat.*;
import static com.gitee.free2free.base.spring.boot.log.AroundType.canPrintRequest;
import static com.gitee.free2free.base.spring.boot.log.AroundType.canPrintResponse;
import static com.gitee.free2free.base.spring.boot.utils.HttpServletUtils.getServletPath;
import static com.gitee.free2free.base.spring.boot.utils.HttpServletUtils.getSessionId;

/**
 * 日志切面
 *
 * @author lfg
 * @version 1.0
 */
@Aspect
@Slf4j
public class AroundLogAspect {

    /**
     * 日志切面
     *
     * @param pjp 切点
     * @param ann 日志注解
     * @return controller返回结果
     * @throws Throwable controller执行异常
     */
    @Around("@annotation(com.gitee.free2free.base.spring.boot.log.AroundLog)&&@annotation(ann)")
    public Object around(ProceedingJoinPoint pjp, AroundLog ann) throws Throwable {

        //打印请求日志
        if (canPrintRequest(ann.type())) {
            log.info(getReqLog(ann, getRequestArgs(pjp)));
        }

        //执行方法
        Object proceed = pjp.proceed();

        //打印返回日志
        if (canPrintResponse(ann.type())) {
            log.info(getResLog(ann, proceed));
        }
        return proceed;
    }

    /**
     * 获取请求日志
     *
     * @param aroundLog log
     * @param reqParam  请求参数
     * @return 日志
     */
    private String getReqLog(AroundLog aroundLog, String reqParam) {
        //构造通用日志
        String reqLog = getCommonLog(aroundLog, DEFAULT_REQUEST_FORMAT);
        //构造request日志
        return reqLog.replace(REQUEST, reqParam);
    }

    /**
     * 获取返回日志
     *
     * @param aroundLog log
     * @param resObject 程序返回结果
     * @return 返回日志
     */
    private String getResLog(AroundLog aroundLog, Object resObject) {
        //构造通用日志
        String resLog = getCommonLog(aroundLog, DEFAULT_RESPONSE_FORMAT);

        //构造response日志
        String resJson;
        if (resObject instanceof String) {
            resJson = String.valueOf(resObject);
        } else {
            resJson = JSONObject.toJSONString(resObject);
        }
        return resLog.replace(RESPONSE, resJson);
    }

    /**
     * 获取通用日志
     *
     * @param logFormat 日志格式
     * @param aroundLog log
     * @return 日志
     */
    private String getCommonLog(AroundLog aroundLog, String logFormat) {
        return logFormat.replace(SESSION_ID, getSessionId())
                .replace(METHOD_NAME, StrUtil.isBlank(aroundLog.value()) ? getServletPath() : aroundLog.value());
    }

    /**
     * 获取请求参数
     *
     * @param joinPoint 切点
     * @return 请求参数
     */
    public String getRequestArgs(JoinPoint joinPoint) {
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
