package com.gitee.free2free.base.spring.boot.properties;

import com.gitee.free2free.base.spring.boot.exception.BaseExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lfg
 * @version 1.0
 */
@Configuration
@ConditionalOnClass(BaseExceptionHandler.class)
public class BaseExceptionHandlerAutoConfigure {

    @Bean
    @ConditionalOnMissingBean
    public BaseExceptionHandler baseExceptionHandler() {
        return new BaseExceptionHandler();
    }

}
