package com.gitee.free2free.base.spring.boot.config;

import com.gitee.free2free.base.spring.boot.log.BaseLogAspect;
import com.gitee.free2free.base.spring.boot.log.BaseLogProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lfg
 * @version 1.0
 */
@Configuration
@ConditionalOnClass(BaseLogAspect.class)
@EnableConfigurationProperties(BaseLogProperties.class)
public class FreeLogAutoConfigure {

    @Autowired
    private BaseLogProperties baseLogProperties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "free2free.base.log", value = "enabled", matchIfMissing = true, havingValue = "true")
    public BaseLogAspect freeLogAspect() {
        return new BaseLogAspect(baseLogProperties.getRequestFormat(), baseLogProperties.getResponseFormat());
    }

}
