package com.kerwin.condition;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-05-12.
 */
@Data
@RequiredArgsConstructor
public class AppealCondition implements Serializable {
    private String name;
    private String phone;
    private String empId;
}
