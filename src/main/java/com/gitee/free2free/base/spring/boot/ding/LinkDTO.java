package com.gitee.free2free.base.spring.boot.ding;

import lombok.Builder;
import lombok.Data;

/**
 * 钉钉文本
 *
 * @author lfg
 * @version 1.0
 */
@Data
@Builder
public class LinkDTO {

    /**
     * 标题
     */
    private String title;

    /**
     * 文本内容
     */
    private String text;

    /**
     * url
     */
    private String messageUrl;

    /**
     * picUrl
     */
    private String picUrl;

}
