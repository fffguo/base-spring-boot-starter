package com.lfg.base.spring.boot.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.http.HttpServletResponseWrapper;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author lfg
 * @version 1.0
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Around("@annotation(com.lfg.base.spring.boot.config.Log)&&@annotation(ann)")
    public Object around(ProceedingJoinPoint pjp, Log ann) throws Throwable {
        String tranceId = UUID.randomUUID().toString().replace("-", "");
        //打印请求日志
        log.info(String.format("traceId:%s,请求接口:[%s],请求参数：%s", tranceId, ann.value(), getRequestArgs(pjp, tranceId)));

        //执行方法
        Object proceed = pjp.proceed();

        //打印返回日志
        log.info(String.format("traceId:%s,请求接口:[%s],返回结果：%s", tranceId, ann.value(), JSONObject.toJSONString(proceed)));
        return proceed;
    }

    public String getRequestArgs(JoinPoint joinPoint, String traceId) {
        try {
            // 参数名
            String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
            // 参数值
            Object[] objs = joinPoint.getArgs();
            Map<String, Object> paramMap = new HashMap<>(16);
            for (int i = 0; i < objs.length; i++) {
                if (!(objs[i] instanceof ExtendedServletRequestDataBinder) && !(objs[i] instanceof HttpServletResponseWrapper)) {
                    paramMap.put(argNames[i], objs[i]);
                }
            }
            if (paramMap.size() > 0) {
                return JSONObject.toJSONString(paramMap);
            }
        } catch (Exception e) {
            log.error("traceId:{},LOG AOP getRequestArgs:", traceId, e);
        }
        return "";
    }

}
