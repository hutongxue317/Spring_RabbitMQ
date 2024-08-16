package com.hjt.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 胡金涛
 * @version 1.0
 * @className Student
 * @date 2024/8/16 13:48
 * @since jdk11
 */
@Data
@Accessors(chain = true)
public class Student {
    private  Integer id;
    private  String name;
    private String brief;
}
