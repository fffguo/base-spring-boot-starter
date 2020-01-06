package com.gitee.free2free.base.spring.boot.properties;

import com.gitee.free2free.base.spring.boot.config.FreeLogAspect;
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
@ConditionalOnClass(FreeLogAspect.class)
@EnableConfigurationProperties(FreeBaseStarterProperties.class)
public class FreeBaseStarterAutoConfigure {


    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "free2free.base.log", value = "enabled", matchIfMissing = true)
    public FreeLogAspect freeLogAspect() {
        return new FreeLogAspect();
    }

}
