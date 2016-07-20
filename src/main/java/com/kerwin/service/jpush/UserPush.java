package com.kerwin.service.jpush;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.PushPayload;
import com.kerwin.common.SendPush;
import com.kerwin.common.SimpleData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Kerwin on 2016/5/6.
 * Pull Watch Info
 */
@Service
public class UserPush {
    private static final Logger log = LoggerFactory.getLogger(UserPush.class);

    /**
     * 获取手表电量
     */
    private static final String MESSAGE_BATTERY = "energy";
    /**
     * 播报天气
     */
    private static final String MESSAGE_WEATHER = "weatherForecast";
    /**
     * 心率
     */
    private static final String MESSAGE_HEART_RATE = "heartRate";
    /**
     * 位置信息
     */
    private static final String MESSAGE_LOCATION = "location";
    /**
     * 运动信息
     */
    private static final String MESSAGE_PLAY = "play";

    @Autowired
    private JPushClientService jPushClientService;

    /**
     * 推送内容
     */
    private PushPayload pushPayload;

    /**
     * 获取推送客户端
     *
     * @return 推送客户端
     */
    private JPushClient getJPushClient() {
        return jPushClientService.getClient("wear");
    }

    /**
     * 通过用户编号给对应的手表用户推送获取位置、电量、运动而等信息的请求
     *
     * @param alias   推送别名
     * @param message 信息类型
     *                watcher      播报天气
     *                battery      获取电量
     *                location     获取位置
     *                heartRate    心率
     *                play         获取运动信息
     * @return .
     */
    public SimpleData pullWatchInfo(String alias, String message) {
        String msg;
        switch (message) {
            case "watcher":
                msg = MESSAGE_WEATHER;
                break;
            case "battery":
                msg = MESSAGE_BATTERY;
                break;
            case "heartRate":
                msg = MESSAGE_HEART_RATE;
                break;
            case "location":
                msg = MESSAGE_LOCATION;
                break;
            case "play":
                msg = MESSAGE_PLAY;
                break;
            default:
                return SimpleData.newItem(-1).setMessage("Failure, The current operation is not supported!");
        }
        pushPayload = PushPayloadService.build_all_alias_message(msg, alias);
        return SendPush.sendPush(getJPushClient(), pushPayload);
    }

    /**
     * 指定手表设定闹钟
     * 久坐提醒、用药提醒
     *
     * @param alias  推送别名
     * @param extras 扩展内容
     * @return 结果：-1 失败（数据不完整）
     */
    public SimpleData setClock(String alias, Map<String, String> extras) {
        pushPayload = PushPayloadService.build_all_alias_message_extras(extras.get("key") + "Notify", alias, extras);
        return SendPush.sendPush(getJPushClient(), pushPayload);
    }
}
