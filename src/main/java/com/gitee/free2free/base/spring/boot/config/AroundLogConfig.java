package com.gitee.free2free.base.spring.boot.config;

import com.gitee.free2free.base.spring.boot.log.AroundLogAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动注入日志切面
 *
 * @author lfg
 * @version 1.0
 */
@Configuration
@ConditionalOnClass(AroundLogAspect.class)
public class AroundLogConfig {


    @Bean
    @ConditionalOnMissingBean
    public AroundLogAspect freeLogAspect() {
        return new AroundLogAspect();
    }

}
