package com.gitee.free2free.base.spring.boot.api.demo;

import lombok.Data;

import java.math.BigDecimal;


/**
 * 坐标转换结果，转换后的坐标顺序与输入顺序一致
 *
 * @author lfg
 * @version 1.0
 */
@Data
public class Location {

    /**
     * 纬度
     */
    private BigDecimal lat;

    /**
     * 经度
     */
    private BigDecimal lng;

}
