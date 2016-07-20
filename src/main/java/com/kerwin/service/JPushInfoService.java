package com.kerwin.service;

import com.kerwin.model.JPushInfo;
import com.kerwin.repository.JPushRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Kerwin on 2016/5/1.
 * JPush Service
 */
@Service
public class JPushInfoService {
    private static final Logger log = LoggerFactory.getLogger(JPushInfoService.class);

    @Autowired
    private JPushRepository jPushRepository;

    /**
     * 通过名称查找JPush信息
     *
     * @param name 名称
     * @return JPush信息
     */
    public JPushInfo findOne(String name) {
        JPushInfo jPushInfo = null;
        try {
            jPushInfo = jPushRepository.findOne(name);
//            jPushInfo = null != jPushInfo ? jPushInfo : new JPushInfo("app", "582858f1787a5adf936fa7b6", "4e55c9cf2f884d1e82c70815");
        } catch (Exception ex) {
            log.error("====================com.moneylike.service.JPushService#findById(name = {})========================", name, ex);
            ex.printStackTrace();
        }
        return jPushInfo;
    }

    /**
     * 新增JPush信息
     *
     * @param jPushInfo JPush信息
     * @return JPush信息
     */
    public JPushInfo create(JPushInfo jPushInfo) {
        return null;
    }

    /**
     * 修改JPush信息
     *
     * @return JPush信息
     */
    public JPushInfo update(JPushInfo jPushInfo) {
        return null;
    }

    /**
     * 删除JPush信息
     *
     * @param jPushInfo JPush信息
     */
    public void delete(JPushInfo jPushInfo) {
        try {
            if (null == jPushInfo || StringUtils.isBlank(jPushInfo.getName()))
                return;
            if (!jPushRepository.exists(jPushInfo.getName()))
                return;
            jPushRepository.delete(jPushInfo);
        } catch (Exception ex) {
            log.error("====================com.moneylike.service.JPushService#delete(JPushInfo.name = {})===============", jPushInfo.getName(), ex);
            ex.printStackTrace();
        }
    }
}
