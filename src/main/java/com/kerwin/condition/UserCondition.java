package com.kerwin.condition;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by Kerwin on 2016-04-20.
 * User查询条件
 */
@Data
@RequiredArgsConstructor
public class UserCondition implements Serializable {
    private static final long serialVersionUID = -2647495279343824525L;
    /**
     * 姓名
     */
    private String name;
    /**
     * 电话
     */
    private String phone;
    /**
     * 性别
     */
    private String gender;
    /**
     * 手表标志
     */
    private String imei;
    /**
     * 服务状态
     */
    private Integer serviceStatus;
}
