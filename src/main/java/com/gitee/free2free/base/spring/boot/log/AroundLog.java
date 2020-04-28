package com.gitee.free2free.base.spring.boot.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 打印请求参数和返回参数
 *
 * @author lfg
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AroundLog {

    /**
     * 没赋值的话，默认取servlet路径
     *
     * @return 方法名
     */
    String value() default "";


    /**
     * 默认只打印请求日志
     *
     * @return AroundType
     */
    AroundType type() default AroundType.REQUEST;

}
