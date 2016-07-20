package com.kerwin.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleData {
    private int _code = 0;
    private String _message = "";
    private Object _data;

    public static SimpleData newItem() {
        return new SimpleData();
    }

    public static SimpleData newItem(Object data) {
        return new SimpleData().setData(data);
    }

    public static SimpleData newItem(int code, Object data) {
        return new SimpleData().setData(data).setCode(code);
    }

    public static SimpleData newItem(int code, String msg, Object data) {
        return new SimpleData().setData(data).setCode(code).setMessage(msg);
    }

    public int getCode() {
        return _code;
    }

    public SimpleData setCode(int _code) {
        this._code = _code;
        return this;
    }

    public String getMessage() {
        return _message;
    }

    public SimpleData setMessage(String _message) {
        this._message = _message;
        return this;
    }

    public Object getData() {
        return _data;
    }

    public SimpleData setData(Object _data) {
        if (_data instanceof List) {
            Map<String, Object> map = new HashMap<>();
            map.put("list", _data);
            this._data = map;
        } else if (_data instanceof String) {
            Map<String, Object> map = new HashMap<>();
            map.put("detail", _data);
            this._data = map;
        } else {
            this._data = _data;
        }
        return this;
    }
}