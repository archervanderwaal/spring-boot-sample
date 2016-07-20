package com.kerwin.controller.json;

import com.kerwin.common.SimpleData;
import com.kerwin.model.AppUser;
import com.kerwin.model.User;
import com.kerwin.service.AppUserService;
import com.kerwin.utils.ValidataUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.kerwin.calculate.AppUserCalculate.calcAppUserInfo;

/**
 * Created by Kerwin on 2016/4/27.
 * AppUser Controller
 */
@RestController
@RequestMapping(value = "/appUser", method = RequestMethod.POST)
public class AppUserJsonController {
    private static final Logger log = LoggerFactory.getLogger(AppUserJsonController.class);
    @Autowired
    private AppUserService appUserService;

    /**
     * 注册新用户
     *
     * @param appUser 用户信息
     * @return 结果
     */
    @RequestMapping(value = "register.json")
    public SimpleData register(AppUser appUser) {
        SimpleData data = new SimpleData();

        // 检查参数是否合法
        if (StringUtils.isBlank(appUser.getPhone()) || StringUtils.isBlank(appUser.getPassword()))
            return data.setCode(-1).setMessage("The phone and password can not be empty!");

        // 判断用户输入的是否为手机号码
        if (!ValidataUtils.isPhone(appUser.getPhone()))
            return data.setCode(-1).setMessage("Please enter the correct phone number!");

        // 注册新用户
        int result = appUserService.save(appUser);
        log.info("appUser/register.json?phone={}&password={}&result={}", appUser.getPhone(), appUser.getPassword(), result);

        // 设置返回结果
        data.setCode(result).setMessage("-2 => Phone number has been registered! & 0 => Failure! & 'other' =>  Success<appUsr id>");
        return data;
    }

    /**
     * 登录
     *
     * @param request 请求
     * @param appUser 用户信息
     * @return 结果
     */
    @RequestMapping(value = "login.json")
    public SimpleData login(HttpServletRequest request, AppUser appUser) {
        SimpleData data = SimpleData.newItem();
        // 检查参数是否合法
        if (StringUtils.isBlank(appUser.getPhone()) || StringUtils.isBlank(appUser.getPassword()))
            return data.setCode(-1).setMessage("The phone and password can not be empty!");

        // 判断用户输入的是否为手机号码
        if (!ValidataUtils.isPhone(appUser.getPhone()))
            return data.setCode(-1).setMessage("Please enter the correct phone number!");

        // 通过手机号码和密码查询用户信息
        int result = appUserService.findByPhoneAndPassword(request, appUser);

        log.info("appUser/login.json?phone={}&password={}&result={}", appUser.getPhone(), appUser.getPassword(), result);

        data.setCode(result).setMessage("-2 => The user not exists! & 0 => Incorrect password & 'other' =>  Success<appUsr id>");
        return data;
    }

    /**
     * 修改app用户首页默认展示的用户
     *
     * @param id     app用户编号
     * @param userId 手表用户编号
     * @return 结果
     */
    @RequestMapping(value = "setDefaultUser.json")
    public SimpleData setDefaultUser(Integer id, Integer userId) {
        SimpleData data = SimpleData.newItem();

        // 判断参数是否合法
        if (null == id || null == userId) return data.setCode(-1).setMessage("The id and userId can not be empty");

        // 判断app用户是否存在
        if (!appUserService.exists(id)) return data.setCode(0).setMessage("The app user not exists!");

        // 设置默认展示用户
        int result = appUserService.setUserIdByAppUserId(id, userId);

        // 设置结果
        data.setCode(result);

        return data;
    }

    /**
     * 通过用户id获取用户信息
     *
     * @param id app用户编号
     * @return 用户信息
     */
    @RequestMapping(value = "getAppUserInfo.json")
    public SimpleData getAppUserInfo(Integer id) {
        SimpleData data = SimpleData.newItem();

        // 检验参数是否合法
        if (null == id) return data.setCode(-1).setMessage("User's ID can not be empty!");

        // 判断用户是否存在
        if (!appUserService.exists(id)) return data.setCode(0).setMessage("User does not exist!");

        // 通过用户编号查询用户信息
        AppUser user = appUserService.findById(id);

        data.setCode(1).setMessage("Success").setData(calcAppUserInfo(user));

        return data;
    }

    /**
     * 获取首页数据
     *
     * @param id app用户id
     * @return 用户信息
     */
    @RequestMapping(value = "getAppUserSimpleInfo.json")
    public SimpleData getAppUserSimpleInfo(Integer id) {
        SimpleData data = SimpleData.newItem();

        // 检验参数是否合法
        if (null == id) return data.setCode(-1).setMessage("User's ID can not be empty!");

        // 判断用户是否存在
        if (!appUserService.exists(id)) return data.setCode(0).setMessage("User does not exist!");

        // 通过用户编号查询用户信息
        AppUser appUser = appUserService.findById(id);

        // 初始化app用户部分数据, 如： 年龄等
        appUser = calcAppUserInfo(appUser);

        // 处理结果
        data.setCode(1).setMessage("Success").setData(appUser.toMap());

        return data;
    }

    /**
     * 修改用户信息
     *
     * @param appUser 用户信息
     * @return .
     */
    @RequestMapping("update.json")
    public SimpleData update(AppUser appUser, String userBirthday) {
        SimpleData data = SimpleData.newItem();

        // 判断参数是否合法
        if (null == appUser.getId()) return data.setCode(-1).setMessage("AppUser id can not empty!");

        // 判断用户是否存在
        if (!appUserService.exists(appUser.getId())) return data.setCode(0).setMessage("AppUser info does not exists!");

        // 转换用户生日格式为java.util.Date;
        if (!StringUtils.isBlank(userBirthday)) {
            Date date = null;
            try {
                // 转换日期
                date = new SimpleDateFormat("yyyy-MM-dd").parse(userBirthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // 设置用户生日
            if (null != date) appUser.setBirthday(date);
        }

        // 修改用户信息
        appUser = appUserService.update(appUser);

        // 判断是否修改成功
        if (null == appUser) data.setCode(0).setMessage("Failure!");
        else data.setCode(1).setMessage("Success!");
        return data;
    }

    /**
     * 修改用户头像
     *
     * @param id         用户编号
     * @param fileStream 头像文件流
     * @param fileName   文件名称
     * @param fileLength 文件大小
     * @return .
     */
    @RequestMapping(value = "updateAvatar.json")
    public SimpleData updateAvatar(Integer id, InputStream fileStream, String fileName, Long fileLength) {
        SimpleData data = SimpleData.newItem();

        // 判断参数是否合法
        if (null == id || null == fileStream || StringUtils.isBlank(fileName) || StringUtils.isBlank(fileName))
            return data.setCode(-1).setMessage("AppUser id. fileStream. fileName. fileLength can not empty!");

        // 判断用户是否存在
        if (!appUserService.exists(id))
            return data.setCode(0).setMessage("AppUser info does not exists!");

        // 上传头像, 并返回结果
        int result = appUserService.updateAvatar(id, fileStream, fileName, fileLength);
        return data.setCode(result);
    }

    /**
     * 修改用户头像
     *
     * @param id   用户编号
     * @param file 头像文件
     * @return .
     */
    @RequestMapping(value = "updateAvatarFile.json")
    public SimpleData updateAvatar(Integer id, MultipartFile file) {
        SimpleData data = SimpleData.newItem();
        // 判断参数是否合法
        if (file.isEmpty()) data.setCode(-1).setMessage("The avatar file can not empty!");
        try {
            // 上传头像
            int result = appUserService.updateAvatar(id, file);

            // 判断上传结果
            if (result == 1) data.setCode(1).setMessage("Success");
            else data.setCode(result).setMessage("The user avatar upload failure!");
        } catch (IOException e) {
            data.setCode(-1).setMessage("Failure, Please try again later!");
            log.error("updateAvatar failure! appUser id is {}", id, e);
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 绑定手表及手表用户
     *
     * @param id        app用户ID
     * @param watchImei 手表标识
     * @return 结果
     */
    @RequestMapping(value = "bindWatch.json")
    public SimpleData bindWatch(Integer id, String watchImei) {
        SimpleData data = SimpleData.newItem();

        // 判断参数是否合法
        if (null == id || StringUtils.isBlank(watchImei))
            return data.setCode(-1).setMessage("User's ID and the watch's IMEI can not be empty!");
        if (15 != watchImei.length()) return data.setCode(-1).setMessage("Watch IMEI length must be 15!");

        // 判断用户是否存在
        if (!appUserService.exists(id)) return data.setCode(0).setMessage("App user does not exist!");

        // 执行绑定操作
        int result = appUserService.bindWatchUser(id, watchImei);
        if (-1 == result) data.setCode(-2).setMessage("The current user is already bound to other App users!");
        return data;
    }

    /**
     * 找回密码
     *
     * @param phone    手机号
     * @param password 密码
     * @return 结果
     */
    @RequestMapping(value = "forgotPassword.json")
    public SimpleData forgotPassword(String phone, String password) {
        int result = appUserService.forgotPassword(phone, password);    // 找回密码
        return SimpleData.newItem().setCode(result);                    // 设置返回结果
    }

    /**
     * 通过app用户编号查询绑定的全部家庭成员消息
     *
     * @param id 用户编号
     * @return 家庭成员信息
     */
    @RequestMapping(value = "getAllFamilyMember.json")
    public SimpleData getAllFamilyMember(Integer id) {
        SimpleData data = SimpleData.newItem();

        // 检查参数是否合法
        if (null == id) return data.setCode(-1).setMessage("The user id can not be empty!");

        // 判断用户是否存在
        if (!appUserService.exists(id)) return data.setCode(0).setMessage("The user info not exists!");

        // 获取全部家庭成员信息
        List<User> users = appUserService.getAllFamilyMember(id);

        // 剔除不重要的数据
        List<Map<String, Object>> userList = users.stream().map(User::toSimpleMap).collect(Collectors.toList());

        // 设置返回结果
        data.setCode(1).setMessage("Success").setData(userList);
        return data;
    }
}
