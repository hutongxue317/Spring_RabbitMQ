package com.hjt.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
public class OrderingOk implements Serializable {
    private Long id;
    private String userName;
    private List<OrderItem> items;
}
