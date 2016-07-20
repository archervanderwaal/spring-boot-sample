package com.kerwin.common;

/**
 * Created by Kerwin on 2016/4/27.
 * 解析结果
 */
public class CalcResultData {
    public static SimpleData setResultData(SimpleData data) {
        switch (data.getCode()) {
            case 0:
                data.setMessage("失败!");
                break;
            case -1:
                data.setMessage("出现错误， 请检查数据");
                break;
        }
        if (data.getCode() >= 1) data.setMessage("成功！");
        return data;
    }
}
