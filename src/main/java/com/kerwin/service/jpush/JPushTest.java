package com.kerwin.service.jpush;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kerwin on 2016/4/30.
 * JPush Click
 */
@Service
public class JPushTest {

    private static final Logger LOG = LoggerFactory.getLogger(JPushTest.class);
    public final String TITLE = "Test from API example";

    // demo App defined in resources/jpush-api.conf
//    protected final String APP_KEY ="582858f1787a5adf936fa7b6";
//    protected final String MASTER_SECRET = "4e55c9cf2f884d1e82c70815";
    public final String ALERT = "后台测试推送==============Alter";
    public final String MSG_CONTENT = "后台测试推送==============MessageInfo";
    public final String REGISTRATION_ID = "0900e8d85ef";
    public final String TAG = "tag_api";

    @Autowired
    private JPushClientService jPushClientService;

    public void testSendPush() {
        // HttpProxy proxy = new HttpProxy("localhost", 3128);
        // Can use this https proxy: https://github.com/Exa-Networks/exaproxy
        // JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        JPushClient jPushClient = jPushClientService.getClient("wear");

        Map<String, String> extras = new HashMap<>();
        extras.put("key", "medicine");
        extras.put("value", "12:00");
        extras.put("type", "1");
        PushPayload pushPayload = PushPayloadService.build_all_alias_message_extras("medicineNotify", "362531821600117", extras);

        // For push, all you need do is to build PushPayload object.
//        PushPayload payload = buildPushObject_all_all_alert();

//        PushPayload pushPayload = PushPayloadService.build_all_all_alert("推送测试========================");
//        PushPayload pushPayload = buildPushObject();

//        PushPayload pushPayload = PushPayload.newBuilder()
//                .setPlatform(Platform.all())
//                .setAudience(Audience.all())
//                .setNotification(Notification.android("Alert推送测试", "=====标题=====", null))
//                .setMessage(Message.content("测试======"))
//                .build();
        try {
            PushResult result = jPushClient.sendPush(pushPayload);
            LOG.info("Got result - " + result);

        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);

        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error MessageInfo: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        }
    }

    public PushPayload buildPushObject() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "孙倩");
//        return PushPayload.newBuilder()
//                .setPlatform(Platform.all())
//                .setAudience(Audience.all())
//                .setNotification(Notification.android(SecurityCodeUtils.getSecurityCode(),"对象推送测试=============孙倩", map))
//                .build();
        return PushPayload.alertAll("===================================================================================");
    }

    public PushPayload buildPushObject_all_alias_alert() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias("alias1"))
                .setNotification(Notification.alert(ALERT))
                .build();
    }

    public PushPayload buildPushObject_android_tag_alertWithTitle() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag("tag1"))
                .setNotification(Notification.android(ALERT, TITLE, null))
                .build();
    }

    public PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag_and("tag1", "tag_all"))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(ALERT)
                                .setBadge(5)
                                .setSound("happy")
                                .addExtra("from", "JPush")
                                .build())
                        .build())
                .setMessage(Message.content(MSG_CONTENT))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .build();
    }

    public PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(MSG_CONTENT)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }
}
