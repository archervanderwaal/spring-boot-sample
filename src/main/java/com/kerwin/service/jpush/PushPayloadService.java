package com.kerwin.service.jpush;

import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Kerwin on 2016/5/1.
 * PushPayload Service ==> 构建推送对象
 */
public class PushPayloadService {

    /**
     * 全部平台_全部设备_Alert
     *
     * @param alert Alert内容
     * @return 推送对象
     */
    public static PushPayload build_all_all_alert(final String alert) {
        return PushPayload.alertAll(alert);
    }

    public static PushPayload build_all_alias_alert(final String alert, final String alias) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.alert(alert))
                .build();
    }

    /**
     * 全部平台_全部设备_Message
     *
     * @param message Message内容
     * @return 推送对象
     */
    public static PushPayload build_all_all_message(final String message) {
        return PushPayload.messageAll(message);
    }

    /**
     * 全部平台_指定设备(别名)_Message
     *
     * @param message Message内容
     * @param alias   别名
     * @return 推送对象
     */
    public static PushPayload build_all_alias_message(final String message, final String alias) {
        Set<String> aliasSet = new HashSet<>();
        aliasSet.add(alias);
        return build_all_alias_message(message, aliasSet);
    }

    /**
     * 全部平台_指定设备(别名)_Message
     *
     * @param message Message内容
     * @param alias   别名集合
     * @return 推送对象
     */
    public static PushPayload build_all_alias_message(final String message, final Set<String> alias) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setMessage(Message.content(message))
                .build();
    }

    /**
     * 全部平台_指定设备(别名)_Message_Extra
     *
     * @param message Message内容
     * @param alias   设备别名
     * @param extras  扩展内容
     * @return 推送对象
     */
    public static PushPayload build_all_alias_message_extras(final String message, final String alias, final Map<String, String> extras) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setMessage(Message.newBuilder().setMsgContent(message).addExtras(extras).build())
                .build();
    }
}