package com.kerwin.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Tiamo on 2016/4/21.
 * 日志
 */
public class LogUtils {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public String index() {

        log.debug("debug level log");
        log.info("info level log");
        log.error("error level log");

        log.info("another info log with {}, {} and {} arguments", 1, "2", 3.0);

        return "";
    }
}
