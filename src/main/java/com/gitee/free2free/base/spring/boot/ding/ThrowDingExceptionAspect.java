package com.gitee.free2free.base.spring.boot.ding;

import com.gitee.free2free.base.spring.boot.other.ThrowDingException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author lfg
 * @version 1.0
 */
@Aspect
@Slf4j
public class ThrowDingExceptionAspect {

    @Around(value = "@annotation(com.gitee.free2free.base.spring.boot.other.ThrowDingException)&&@annotation(ding)")
    public Object around(ProceedingJoinPoint pjp, ThrowDingException ding) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
        //执行方法
        return pjp.proceed();
    }

}
