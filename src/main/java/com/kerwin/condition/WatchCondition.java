package com.kerwin.condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-05-11.
 * 设备管理的条件查询类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WatchCondition implements Serializable {
    private static final long serialVersionUID = -2722766566709060542L;
    private String name;
    private String phone;
    private String watchImei;
}
