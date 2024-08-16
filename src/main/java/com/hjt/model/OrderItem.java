package com.hjt.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author 胡金涛
 * @version 1.0
 * @className RegisterOk
 * @date 2024/8/13 19:51
 * @since jdk11
 */
@Data
@Accessors(chain = true)
public class OrderItem implements Serializable {
    /*订单编号*/
    private Integer id;
    /*商品编号*/
    private Integer productId;
    
    private String productName;
    private Integer qty;
    private BigDecimal price;
}
