package com.kerwin.controller;

import com.kerwin.common.SimpleData;
import com.kerwin.service.SendMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kwok on 2016/4/25.
 * Send Message
 */
@RestController
@RequestMapping(value = "/send", method = RequestMethod.POST)
public class SendMessageController {

    private static final Logger log = LoggerFactory.getLogger(SendMessageController.class);
    @Autowired
    SendMessageService sendMessageService;

    @RequestMapping(value = "message.json")
    public SimpleData sendMessage(String phone) {
        log.info("================================phone:{}==============================================================", phone);
        return sendMessageService.sendSms(phone);
    }
}
