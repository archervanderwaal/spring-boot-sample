package com.kerwin.condition;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by Kerwin on 2016/5/8.
 * Query sleep quality condition
 */
@Data
@RequiredArgsConstructor
public class Condition implements Serializable {
    private static final long serialVersionUID = 2756650312564940120L;

    /**
     * 姓名
     */
    String name;
    /**
     * 手表标识
     */
    String imei;
}
