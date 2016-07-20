package com.kerwin.service.jpush;

import cn.jpush.api.JPushClient;
import com.kerwin.model.JPushInfo;
import com.kerwin.service.JPushInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Kerwin on 2016/5/1.
 * JPushClient by App
 */
@Service
public class JPushClientService {
    private static final Logger log = LoggerFactory.getLogger(JPushClientService.class);

    @Autowired
    private JPushInfoService jPushInfoService;

    /**
     * 通过名称及最大重试时间取得JPush客户端
     *
     * @param name 名称
     * @return 推送客户端
     */
    public JPushClient getClient(String name) {
        JPushInfo jPushInfo = jPushInfoService.findOne(name);
        return getClient(jPushInfo.getAppKey().trim(), jPushInfo.getMasterSecret().trim());
    }

    /**
     * 通过AppKey MasterSecret MaxRetryTimes 构建JPushClient
     *
     * @param appKey       APPKey
     * @param masterSecret MasterSecret
     * @return JPushClient
     */
    private JPushClient getClient(String appKey, String masterSecret) {
        log.info("JPushService#getClient(appKey = {}, masterSecret = {})======================", appKey, masterSecret);
        return new JPushClient(masterSecret, appKey);
    }
}
