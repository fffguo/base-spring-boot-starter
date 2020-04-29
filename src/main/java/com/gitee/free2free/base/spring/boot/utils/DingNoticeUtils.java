package com.gitee.free2free.base.spring.boot.utils;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 钉钉工具类
 *
 * @author lfg
 * @version 1.0
 */
@Slf4j
public class DingNoticeUtils {

    private static final String FORMAT = "%s&timestamp=%s&sign=%s";

    /**
     * 构造钉钉通知请求url
     *
     * @param webHook webHook
     * @param secret  秘钥
     * @return url
     */
    public static String constructRequestUrl(String webHook, String secret) {
        long time = System.currentTimeMillis();
        if (StrUtil.isBlank(secret)) {
            return webHook;
        } else {
            return String.format(FORMAT, webHook, time, sign(secret, time));
        }
    }

    /**
     * 生成签名
     *
     * @param secret            秘钥
     * @param currentTimeMillis 当前时间毫秒数
     * @return 签名
     */
    private static String sign(String secret, Long currentTimeMillis) {
        try {
            String stringToSign = currentTimeMillis + "\n" + secret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
            return URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeyException e) {
            log.error("钉钉生成签名失败：{}", e.getMessage(), e);
            return "";
        }
    }

}
