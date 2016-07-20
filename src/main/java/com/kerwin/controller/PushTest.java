package com.kerwin.controller;

import com.kerwin.service.jpush.JPushTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kerwin on 2016/5/8.
 * Push Test
 */
@RestController
@RequestMapping(value = "/push", method = RequestMethod.GET)
public class PushTest {

    @Autowired
    private JPushTest jPushTest;

    @RequestMapping(value = "medicenNotify.json")
    public void pushMedicenNotify() {
        jPushTest.testSendPush();
    }
}
