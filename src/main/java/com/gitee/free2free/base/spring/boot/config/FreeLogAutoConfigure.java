package com.gitee.free2free.base.spring.boot.config;

import com.gitee.free2free.base.spring.boot.log.AroundLogAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FreeLogAutoConfigure
 *
 * @author lfg
 * @version 1.0
 */
@Configuration
@ConditionalOnClass(AroundLogAspect.class)
public class FreeLogAutoConfigure {


    @Bean
    @ConditionalOnMissingBean
    public AroundLogAspect freeLogAspect() {
        return new AroundLogAspect();
    }

}
