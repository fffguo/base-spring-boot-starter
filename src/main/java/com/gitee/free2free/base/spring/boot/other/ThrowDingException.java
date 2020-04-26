package com.gitee.free2free.base.spring.boot.other;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解加在异常拦截的方法处，用于监控出现异常后，
 * 发钉钉消息通知开发人员
 *
 * @author lfg
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ThrowDingException {

    /**
     * @return 方法名
     */
    String value() default "";
}
