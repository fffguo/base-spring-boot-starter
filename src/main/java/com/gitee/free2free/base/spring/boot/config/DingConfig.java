package com.gitee.free2free.base.spring.boot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 订单消息的配置属性
 *
 * @author lfg
 * @version 1.0
 */
@Data
@ConfigurationProperties(prefix = "base.ding")
public class DingConfig {

    /**
     * webHook
     */
    private String webHook;

    /**
     * 签名秘钥
     */
    private String secret;

    /**
     * 跳转url,钉钉通知点击打开后的页面，程序会自动在该页面后拼接?sessionId=xxx&msg=xxx
     * 例如：http://image.zxzy.xyz/show_msg.html?sessionId=xxx&msg=xxx
     */
    private String skipUrl = "http://image.zxzy.xyz/show_msg.html";

    /**
     * 钉钉通知图片url
     */
    private String pictureUrl = "http://image.zxzy.xyz/bug.png";

}
