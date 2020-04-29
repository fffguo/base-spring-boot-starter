package com.gitee.free2free.base.spring.boot.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * HttpServletUtils
 *
 * @author lfg
 * @version 1.0
 */
@Slf4j
public class HttpServletUtils {

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static String getSessionId() {
        return getHttpServletRequest().getSession().getId();
    }

    public static String getServletPath() {
        return getHttpServletRequest().getServletPath();
    }

}
