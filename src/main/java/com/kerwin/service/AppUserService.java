package com.kerwin.service;

import com.kerwin.condition.AppUserCondition;
import com.kerwin.model.AppUser;
import com.kerwin.model.User;
import com.kerwin.repository.AppUserRepository;
import com.kerwin.utils.IPUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

import static com.kerwin.calculate.UserCalculate.calcUserInfo;

/**
 * Created by Kerwin on 2016/4/26.
 * AppUser Service
 */
@Service
public class AppUserService {
    private static final Logger log = LoggerFactory.getLogger(AppUserService.class);

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UploadService uploadService;

    /**
     * 保存App用户信息(新增)
     *
     * @param appUser App用户信息
     * @return -2 => 手机号已经被注册 & -1 => 数据不完整 & 0 => 失败 & "other" 成功（APP用户ID）
     */
    public Integer save(AppUser appUser) {
        // 通过手机号码查询用户信息
        AppUser temp = findByPhone(appUser.getPhone());

        // 判断手机号码是否已经被注册
        if (null != temp) return -2;

        // 如果没有被注册, 设置注册时间
        appUser.setRegisterTime(new Date());

        // 创建新的用户
        temp = appUserRepository.save(appUser);
        return null == temp ? 0 : temp.getId();
    }

    /**
     * 保存App用户信息(修改)
     *
     * @param appUser App用户信息
     * @return .
     */
    public AppUser update(AppUser appUser) {
        AppUser temp = findById(appUser.getId());

        // 姓名
        if (!StringUtils.isBlank(appUser.getName())) temp.setName(appUser.getName());

        // 个性签名
        if (!StringUtils.isBlank(appUser.getSign())) temp.setSign(appUser.getSign());

        // 自我介绍
        if (!StringUtils.isBlank(appUser.getRemark())) temp.setRemark(appUser.getRemark());

        // 生日
        if (null != appUser.getBirthday()) temp.setBirthday(appUser.getBirthday());

        // 身高
        if (null != appUser.getHeight()) temp.setHeight(appUser.getHeight());

        // 体重
        if (null != appUser.getWeight()) temp.setWeight(appUser.getWeight());

        // 性别
        if (null != appUser.getGender()) temp.setGender(appUser.getGender());

        // 修改信息
        return appUserRepository.save(temp);
    }

    /**
     * 修改用户头像
     *
     * @param id   用户编号
     * @param file 头像
     * @return .
     * @throws IOException
     */
    public Integer updateAvatar(Integer id, MultipartFile file) throws IOException {
        return updateAvatar(id, file.getInputStream(), file.getOriginalFilename(), file.getSize());
    }

    /**
     * 修改用户头像
     *
     * @param id         用户编号
     * @param fileStream 头像文件输入流
     * @param fileName   文件名称
     * @param fileLength 文件大小
     * @return .
     */
    public Integer updateAvatar(Integer id, InputStream fileStream, String fileName, Long fileLength) {
        // 上传头像
        String avatar = uploadService.fileUpload(fileStream, fileName, fileLength);

        // 判断是否上传成功
        if (avatar.equals("Failure")) return -1;

        // 如果头像成功上传, 修改数据库对应的头像路径
        return appUserRepository.updateAvatar(avatar, id);
    }

    /**
     * 绑定手表用户
     *
     * @param id   APP用户ID
     * @param imei 手表唯一标识
     * @return .
     */
    public Integer bindWatchUser(Integer id, String imei) {
        return userService.bindWatch(imei, id);
    }

    /**
     * 通过手机号及密码判断用户是否存在
     *
     * @param appUser APP用户
     * @return -2 => 用户不存在 & 0 => 密码不正确 & "other" =>  成功 （APP用户编号）
     */
    public Integer findByPhoneAndPassword(HttpServletRequest request, AppUser appUser) {
        AppUser temp;
        int result;

        // 通过手机号码查找用户信息
        temp = findByPhone(appUser.getPhone());

        // 判断用户是否存在
        if (null == temp) return -2;

        // 判断用户输入的密码是否正确
        result = temp.getPassword().equals(appUser.getPassword()) ? temp.getId() : 0;

        // 如果密码正确, 修改上次登录时间等信息
        if (0 < result) {
            // 获取用户上次登录时间
            Date lastLongDate = temp.getLastLoginTime();

            // 截取用户上次登录日期
            String lastDate = new DateTime(lastLongDate).toString("yyyy-MM-dd");

            // 获取当前日期
            String date = new DateTime().toString("yyyy-MM-dd");

            // 如果是当天第一次登陆, 登陆次数+1, 反之略过
            if (!lastDate.equalsIgnoreCase(date)) {
                // 获取登录次数
                Integer count = temp.getLoginCount();

                // 如果登陆次数为null, 设置为0, 反之+1
                temp.setLoginCount(null == count ? 0 : ++count);
            }

            // 上次登录IP
            temp.setLastLoginIp(IPUtils.getIpAddress(request));

            // 上次登录时间
            temp.setLastLoginTime(new Date());

            // 修改用户信息
            temp = update(temp);
            return null == temp ? 0 : temp.getId();
        }
        return result;
    }

    /**
     * 找回密码
     *
     * @param phone    App用户手机号
     * @param password 密码
     * @return -2 => App用户不存在 & -1 => 数据不完整 & 0 => 失败 & 1 => 成功（APP用户编号）
     */
    @Transactional
    public Integer forgotPassword(String phone, String password) {
        // 判断用户是否存在
        AppUser temp = findByPhone(phone);
        if (null == temp) return -2;

        // 修改密码
        return appUserRepository.updatePassword(password, phone);
    }

    /**
     * 通过手机号码查询用户信息
     *
     * @param id 手机号码
     * @return 用户信息
     */
    public AppUser findById(Integer id) {
        // 判断参数是否合法
        if (null == id) return null;

        // 通过id查询用户信息
        AppUser tempUser = appUserRepository.findOne(id);

        // 初始化手表用户信息
        if (null != tempUser.getUserId()) {                         // 判断app用户是否设置了默认展示的家庭成员
            User user = userService.findById(tempUser.getUserId()); // 如果有设置, 通过设置的用户的编号查询用户信息
            tempUser.setUser(calcUserInfo(user));                   // 为app用户设置临时的手表用户信息
        }

        return tempUser;
    }

    /**
     * 通过app用户编号修改用户编号
     *
     * @param id     手机用户编号
     * @param userId 手表用户编号
     * @return .
     */
    @Transactional
    public Integer setUserIdByAppUserId(Integer id, Integer userId) {
        // 判断手表用户是否存在
        if (!userService.exists(userId)) return 0;

        // 执行修改
        return appUserRepository.updateUserIdById(id, userId);
    }

    /**
     * 通过APP用户编号判断用户是否存在
     *
     * @param id 用户编号
     * @return true => 存在 & false => 不存在
     */
    public Boolean exists(Integer id) {
        return null != id && appUserRepository.exists(id);
    }

    /**
     * 通过用户编号查询全部绑定的家庭成员的信息
     *
     * @param id 用户编号
     * @return 已经绑定的家庭成员的信息
     */
    public List<User> getAllFamilyMember(Integer id) {
        return userService.findByAppUserId(id);
    }

    /**
     * 通过用户id和日期获取用户历史健康信息
     *
     * @param id   用户id
     * @param date 日期
     * @return 健康信息
     */
    public Map<String, Object> getUserHistory(Integer id, Date date) {
        return null;
    }

    /**
     * 通过手机号码查询用户信息
     *
     * @param phone 手机号码
     * @return 用户信息
     */
    private AppUser findByPhone(String phone) {
        return StringUtils.isBlank(phone) ? null : appUserRepository.findByPhone(phone);
    }

    /**
     * 性别比例统计
     *
     * @return 性别比例
     */
    public Map<String, Object> sex_ratio_statistic(Map<String, Object> map) {

        // 查询数据库中男性用户的总人数
        long appMale = appUserRepository.findByGender("男");

        // 查询数据库中女性用户的总人数
        long appFemale = appUserRepository.findByGender("女");

        //查询数据库中保密用户的总人数
        long appUnknown = appUserRepository.findByGender("未知");

        // 查询数据库中总用户数
        long appCount = appUserRepository.count();

        // 将浮点转换为百分比
        DecimalFormat nf = (DecimalFormat) NumberFormat.getPercentInstance();

        //00表示小数点2位
        nf.applyPattern("00%");

        //2表示精确到小数点后2位
        nf.setMaximumFractionDigits(2);

        // 将查询及计算的结果放到集合并返回
        map.put("appMale", appMale);                                                  // 男性人数
        map.put("appFemale", appFemale);                                              // 女性人数
        map.put("appUnknown", appUnknown);                                             //保密人数
        map.put("appCount", appCount);                                                // 总人数
        map.put("appMaleRatio", nf.format((double) appMale / (double) appCount));        // 男性占总人数的百分比
        map.put("appFemaleRatio", nf.format((double) appFemale / (double) appCount));    // 女性占总人数的百分比
        map.put("appUnknownRatio", nf.format((double) appUnknown / (double) appCount));  // 未知占总人数的百分比
        map.put("appCountRatio", nf.format((double) appMale + (double) appUnknown + (double) appUnknown));  // 获取总人数
        return map;
    }


    /**
     * 动态分页查询
     *
     * @param appUserCondition  筛选条件
     * @param pageable     分页信息
     * @return  用户信息
     */
    public Page<AppUser> findAll(AppUserCondition appUserCondition, Pageable pageable) {
        // 获取要查询的页数
        int page = pageable.getPageNumber();
        // 由于SpringDate的分页默认从0开始,将页数-1
        if (page != 0)
            pageable=new PageRequest(page -1, pageable.getPageSize(),pageable.getSort());
        return appUserRepository.findAll((root, query, cb) -> {
            //查询条件
            List<Predicate> predicates = new ArrayList<>();

            //姓名
            if (!StringUtils.isBlank(appUserCondition.getName()))
                predicates.add(cb.like(root.get("name").as(String.class), "%" + appUserCondition.getName() + "%"));
            //电话号码
            if (!StringUtils.isBlank(appUserCondition.getPhone()))
                predicates.add(cb.equal(root.get("phone").as(String.class), appUserCondition.getPhone()));
            //性别
            if (!StringUtils.isBlank(appUserCondition.getGender()) && !appUserCondition.getGender().equalsIgnoreCase("全部"))
                predicates.add(cb.equal(root.get("gender").as(String.class), appUserCondition.getGender()));

            //加入条件
            query.where(predicates.toArray(new Predicate[predicates.size()]));
            return  null;
        },pageable);
    }

    /**
     * 新增用户信息
     *
     * @param appUser 用户信息
     * @return 用户信息
     */
    public AppUser create(AppUser appUser) {
        return null == appUser ? null : appUserRepository.saveAndFlush(appUser);
    }


//    /**
//     * 修改用户信息
//     *
//     * @param appUser 用户信息
//     * @return
//     */
//    public AppUser update(AppUser appUser){
//        AppUser temp = findById(appUser.getId());
//
//        //用户名
//        if (StringUtils.isNotBlank(appUser.getName())) {
//            String name = appUser.getName();
//            if (10 > name.length())
//                temp.setName(appUser.getName());
//        }
//
//        //性别
//        if (StringUtils.isNotBlank(appUser.getGender())) {
//            String gender = appUser.getGender().trim();
//            if (2 == gender.length() && (gender.equalsIgnoreCase("男") || gender.equalsIgnoreCase("女")))
//                temp.setGender(appUser.getGender());
//        }
//
//        //生日
//        if (null != appUser.getBirthday())
//            temp.setBirthday(appUser.getBirthday());
//
//        //手机号码
//        if (StringUtils.isNotBlank(appUser.getPhone())) {
//            String phone = appUser.getPhone();
//            if (11 == phone.length() && ValidataUtils.isPhone(phone))
//                temp.setPhone(appUser.getPhone());
//        }
//
//        //密码
//        if (StringUtils.isNotBlank(appUser.getPassword())) {
//            String password =appUser.getPassword();
//            if (6 >= password.length())
//                temp.setPassword(appUser.getPassword());
//        }
//
//        //头像
//        if (StringUtils.isNotBlank(appUser.getAvatar())) {
//            String avatar =appUser.getAvatar();
//            temp.setAvatar(avatar);
//        }
//
//        //余额
//        if (null != appUser.getBalance()) {
//            temp.setBalance(appUser.getBalance());
//        }
//
//        //上次登录时间
//        if (null != appUser.getLastLoginTime()) {
//            temp.setLastLoginTime(appUser.getLastLoginTime());
//        }
//
//        //上次登录IP
//        if (StringUtils.isNotBlank(appUser.getLastLoginIp())) {
//            String lastLoginIp =appUser.getLastLoginIp();
//            if (15 == lastLoginIp.length())
//                temp.setLastLoginIp(appUser.getLastLoginIp());
//        }
//
//        //登录次数
//        if (null != appUser.getLoginCount())
//            temp.setLoginCount(appUser.getLoginCount());
//
//        //个性签名
//        if (StringUtils.isNotBlank(appUser.getSign()))
//            temp.setSign(appUser.getSign());
//
//        //自我介绍
//        if (StringUtils.isNotBlank(appUser.getRemark()))
//            temp.getRemark();
//
//        //用户体重
//        if (null != appUser.getHeight())
//            temp.setHeight(appUser.getHeight());
//
//        //用户身高
//        if (null != appUser.getWeight())
//            temp.setWeight(appUser.getWeight());
//
//        //当前位置经度
//        if (StringUtils.isNotBlank(appUser.getLongitude())) {
//            String longitude = appUser.getLongitude();
//            temp.setLongitude(longitude);
//        }
//
//        //当前位置纬度
//        if (StringUtils.isNotBlank(appUser.getLatitude())) {
//            String latitude = appUser.getLatitude();
//            temp.setLatitude(latitude);
//        }
//
//        //奖励记录
//        if (null != appUser.getReward())
//            temp.setReward(appUser.getReward());
//
//        //年龄
//        if (null != appUser.getAge())
//            temp.setAge(appUser.getAge());
//
//        //孝心指数
//        if (null != appUser.getFilialScore())
//            temp.setFilialScore(appUser.getFilialScore());
//
//
//        return appUserRepository.save(calcAppUserInfo(temp));
//    }


}
