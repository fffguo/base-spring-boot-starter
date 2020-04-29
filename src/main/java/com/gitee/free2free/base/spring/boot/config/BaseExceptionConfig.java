package com.gitee.free2free.base.spring.boot.config;

import com.gitee.free2free.base.spring.boot.exception.BaseExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动注入异常处理
 *
 * @author lfg
 * @version 1.0
 */
@Configuration
@ConditionalOnClass(BaseExceptionHandler.class)
public class BaseExceptionConfig {

    @Bean
    @ConditionalOnMissingBean
    public BaseExceptionHandler baseExceptionHandler() {
        return new BaseExceptionHandler();
    }

}
