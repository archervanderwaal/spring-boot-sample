package com.kerwin.service;

import com.kerwin.common.SimpleData;
import com.kerwin.utils.SecurityCodeUtils;
import com.kerwin.utils.SmsClientAccessUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Kerwin on 2016-04-25.
 * 发送验证码
 */
@Service
public class SendMessageService {

    private static final Logger log = LoggerFactory.getLogger(SendMessageService.class);

    public SimpleData sendSms(String phone, Integer length, Integer level) {
        SimpleData simpleData = SimpleData.newItem();
        String code;
        switch (level) {
            case 1:
                code = SecurityCodeUtils.getSecurityCode(length, SecurityCodeUtils.SecurityCodeLevel.Simple, true);
                break;
            case 2:
                code = SecurityCodeUtils.getSecurityCode(length, SecurityCodeUtils.SecurityCodeLevel.Medium, true);
                break;
            case 3:
                code = SecurityCodeUtils.getSecurityCode(length, SecurityCodeUtils.SecurityCodeLevel.Hard, true);
                break;
            default:
                code = SecurityCodeUtils.getSecurityCode(length, SecurityCodeUtils.SecurityCodeLevel.Simple, true);
        }
        String url = "http://xtx.telhk.cn:8080/sms.aspx";
        String userid = "6600";
        String account = "dianfutong";
        String password = "dianfutong";
        String content = "【MoneyLike】玛尼莱克注册验证码为：" + code;
        log.debug("===============================验证码为{}================================", code);
        String send = sendSms(url, "send", userid, account, password, phone, content);
        try {
            System.out.println(new String(send.getBytes("UTF-8")));
            if (new String(send.getBytes("UTF-8")).contains("ok")) {
                send = "ok";
                simpleData.setCode(1).setMessage("ok").setData(code);
            } else {
                simpleData.setCode(-1).setMessage("failure");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            simpleData.setCode(-1).setMessage("出现错误！");
        }
        return simpleData;
    }

    public SimpleData sendSms(String phone, Integer length) {
        return sendSms(phone, length, 1);
    }

    public SimpleData sendSms(String phone) {
        return sendSms(phone, 6);
    }

    /**
     * 发送信息方法1--必须传入必填内容
     * 其一：发送方式，默认为POST<br/>
     * 其二：发送内容编码方式，默认为UTF-8
     *
     * @param url      ：必填--发送连接地址URL--比如>http://xtx.telhk.cn:8080/sms.aspx
     * @param userid   ：必填--用户ID，为数字
     * @param account  ：必填--用户帐号
     * @param password ：必填--用户密码
     * @param mobile   ：必填--发送的手机号码，多个可以用逗号隔比如>13512345678,13612345678
     * @param content  ：必填--实际发送内容，
     * @return 返回发送信息之后返回字符串
     */
    private String sendSms(String url, String action, String userid, String account, String password, String mobile, String content) {

        return sendSms(url, action, userid, account, password, mobile, content, "",
                "POST", "UTF-8", "UTF-8");
    }

    /**
     * 发送信息方法--暂时私有化，这里仅仅是提供用户接口而已。其实用不了那么复杂
     * 发送信息最终的组合形如
     *
     * @param url           ：必填--发送连接地址URL--比如
     * @param userid        ：必填--用户ID，为数字
     * @param account       ：必填--用户帐号
     * @param password      ：必填--用户密码
     * @param mobile        ：必填--发送的手机号码，多个可以用逗号隔比如
     * @param content       ：必填--实际发送内容，
     * @param action        ：选填--访问的事件，默认为send
     * @param sendTime      ：选填--定时发送时间，不填则为立即发送，时间格式
     * @param sendType      ：选填--发送方式，默认为POST
     * @param codingType    ：选填--发送内容编码方式，默认为UTF-8
     * @param backEncodType ：选填--返回内容编码方式，默认为UTF-8
     * @return 返回发送之后收到的信息
     */
    private String sendSms(String url, String action, String userid, String account, String password, String mobile, String content, String sendTime, String sendType, String codingType, String backEncodType) {
        StringBuilder send = new StringBuilder();
        try {
            if (codingType == null || codingType.equals("")) {
                codingType = "UTF-8";
            }
            if (backEncodType == null || backEncodType.equals("")) {
                backEncodType = "UTF-8";
            }

            if (action != null && !action.equals("")) {
                send.append("action=").append(action);
            } else {
                send.append("action=send");
            }
            send.append("&userid=").append(userid);
            send.append("&account=").append(URLEncoder.encode(account, codingType));
            send.append("&password=").append(URLEncoder.encode(password, codingType));
            send.append("&mobile=").append(mobile);
            send.append("&content=").append(URLEncoder.encode(content, codingType));
            if (sendTime != null && !sendTime.equals("")) {
                send.append("&sendTime=").append(URLEncoder.encode(sendTime, codingType));
            }
            if (sendType != null && (sendType.toLowerCase()).equals("get")) {
                return SmsClientAccessUtils.getInstance().doAccessHTTPGet(url + "?" + send.toString(), backEncodType);
            } else {
                return SmsClientAccessUtils.getInstance().doAccessHTTPPost(url, send.toString(), backEncodType);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "未发送，编码异常";
        }
    }
}
