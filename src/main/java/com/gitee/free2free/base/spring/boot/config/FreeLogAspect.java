package com.gitee.free2free.base.spring.boot.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author lfg
 * @version 1.0
 */
@Aspect
@Slf4j
public class FreeLogAspect {

    @Around("@annotation(com.gitee.free2free.base.spring.boot.config.FreeLog)&&@annotation(freeLog)")
    public Object around(ProceedingJoinPoint pjp, FreeLog freeLog) throws Throwable {
        String tranceId = UUID.randomUUID().toString().replace("-", "");
        //打印请求日志
        log.info(String.format("traceId:%s,请求接口:[%s],请求参数：%s", tranceId, freeLog.value(), getRequestArgs(pjp, tranceId)));

        //执行方法
        Object proceed = pjp.proceed();

        //打印返回日志
        log.info(String.format("traceId:%s,请求接口:[%s],返回结果：%s", tranceId, freeLog.value(), JSONObject.toJSONString(proceed)));
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
                if (objs[i] instanceof BeanPropertyBindingResult
                        || objs[i] instanceof ServletRequest
                        || objs[i] instanceof ServletResponse) {
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
            log.error("traceId:{},LOG AOP getRequestArgs:{}", traceId, e.getMessage(), e);
        }
        return "";
    }

}
