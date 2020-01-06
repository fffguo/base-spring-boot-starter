package com.gitee.free2free.base.spring.boot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lfg
 * @version 1.0
 */
@ConfigurationProperties(prefix = "free2free.base.log")
public class FreeBaseStarterProperties {

    /**
     * 是否开启日志
     */
    private Boolean enabled;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
