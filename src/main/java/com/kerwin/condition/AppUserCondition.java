package com.kerwin.condition;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-05-26.
 *
 */
@Data
@RequiredArgsConstructor
public class AppUserCondition implements Serializable {

    private static final long serialVersionUID = 395182076237537353L;
    /**
     * 用户名
     */
    private String name;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 性别
     */
    private String gender;

}
