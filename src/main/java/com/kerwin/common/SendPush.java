package com.kerwin.common;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Kerwin on 2016/5/3.
 * 推送消息
 */
public class SendPush {
    private static final Logger log = LoggerFactory.getLogger(SendPush.class);

    /**
     * 推送消息
     *
     * @param jPushClient 客户端
     * @param pushPayload 消息
     * @return 结果
     */
    public static SimpleData sendPush(JPushClient jPushClient, PushPayload pushPayload) {
        SimpleData data = SimpleData.newItem();
        try {
            PushResult result = jPushClient.sendPush(pushPayload);
            if (result.isResultOK()) data.setCode(1).setMessage("成功！");
            log.info("Got result - " + result);
        } catch (APIConnectionException e) {
            log.error("Connection error. Should retry later. ", e);
            data.setCode(-1).setMessage("Connection error. Should retry later");
        } catch (APIRequestException e) {
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error MessageInfo: " + e.getErrorMessage());
            log.info("Msg ID: " + e.getMsgId());
            data.setCode(-1).setMessage("Error response from JPush server. Should review and fix it.");
        }
        return data;
    }
}
