package com.kerwin.service.jpush;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.PushPayload;
import com.kerwin.common.SendPush;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Kerwin on 2016/5/6.
 * AppUser Pull And Push
 */
@Service
public class AppUserPush {

    @Autowired
    private JPushClientService jPushClientService;

    private PushPayload pushPayload;

    private JPushClient getJPushClient() {
        return jPushClientService.getClient("app");
    }

    /**
     * 给手机App用户推送手表位置信息
     *
     * @param message 消息内容
     * @param alias   设备别名
     */
    public void pushLocation(String message, String alias, Map<String, String> extras) {

        // 构建推送内容对象
//        pushPayload = PushPayloadService.build_all_alias_message_extras(message, alias, extras);

        pushPayload = PushPayloadService.build_all_all_alert(extras.toString());

        // 推送消息
        SendPush.sendPush(getJPushClient(), pushPayload);
    }

    /**
     * 给手机App用户推送手表用户心率信息
     *
     * @param alias 要推送的设备的别名
     * @param value 心率值
     */
    public void pushHeartRate(String alias, Integer value) {

        // 构建推送内容对象
//        pushPayload = PushPayloadService.build_all_alias_message(value.toString(), alias);

        pushPayload = PushPayload.alertAll(value.toString());

        // 推送消息
        SendPush.sendPush(getJPushClient(), pushPayload);
    }

    /**
     * 给手机App用户推送手表电量信息
     *
     * @param battery 电量
     * @param name    称呼
     * @param alias   要推送的设备的别名
     */
    public void pushBattery(Integer battery, String name, String alias) {

        // 拼接电量提醒字符串
        String content = name + "的电量剩余" + battery + "%了, 请提醒他充电!";

        // 构建推送内容对象
        pushPayload = PushPayloadService.build_all_alias_alert(content, alias);
//        pushPayload = PushPayload.alertAll(content);

        // 推送消息
        SendPush.sendPush(getJPushClient(), pushPayload);
    }
}
