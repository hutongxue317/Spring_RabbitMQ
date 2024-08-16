package com.hjt.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 胡金涛
 * @version 1.0
 * @className RegisterOk
 * @date 2024/8/13 19:51
 * @since jdk11
 */
@Data
@Accessors(chain = true)
public class RegisterOk implements Serializable {
    /** id*/
    private Integer id;
    /*用户名*/
    private  String nickName;
    /*手机号*/
    private String tel;
}
