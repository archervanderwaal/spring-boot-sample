package com.kerwin.service;

import com.kerwin.common.SimpleData;
import com.kerwin.condition.UserCondition;
import com.kerwin.model.AppUser;
import com.kerwin.model.HealthAnalysis;
import com.kerwin.model.User;
import com.kerwin.repository.UserRepository;
import com.kerwin.service.jpush.AppUserPush;
import com.kerwin.service.jpush.UserPush;
import com.kerwin.utils.DateUtils;
import com.kerwin.utils.ValidataUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

import static com.kerwin.calculate.UserCalculate.calcUserInfo;

/**
 * Created by Kerwin on 2016-04-26.
 * User Service
 */
@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private UserPush userPush;
    @Autowired
    private AppUserPush appUserPush;
    @Autowired
    private HealthAnalysisService healthAnalysisService;
    @Autowired
    private AppBindWatchService appBindWatchService;

    /**
     * 通过用户编号判断用户是否存在
     *
     * @param userId 用户编号
     * @return true => 存在 & false => 不存在
     */
    public Boolean exists(Integer userId) {
        return null != userId && userRepository.exists(userId);
    }

    /**
     * 通过用户编号查询用户信息
     *
     * @param id 用户编号
     * @return 用户信息
     */
    public User findById(Integer id) {
        return null == id ? null : userRepository.findOne(id);
    }

    /**
     * 通过手表imei查询用户信息
     *
     * @param imei 手表imei
     * @return 用户信息
     */
    public User findByImei(String imei) {
        return StringUtils.isBlank(imei) || 15 != imei.length() ? null : userRepository.findByImei(imei);
    }

    /**
     * 动态筛选用户信息
     *
     * @param condition 筛选条件
     * @param pageable  分页信息
     * @return 用户信息
     */
    public Page<User> findAll(UserCondition condition, Pageable pageable) {
        // 获取要查询的页数
        int page = pageable.getPageNumber();

        // 由于springDate的分页默认从0开始, 将页数-1
        if (page != 0)
            pageable = new PageRequest(page - 1, pageable.getPageSize(), pageable.getSort());

        return userRepository.findAll((root, query, cb) -> {
            // 查询条件
            List<Predicate> predicates = new ArrayList<>();

            // 姓名
            if (!StringUtils.isBlank(condition.getName()))
                predicates.add(cb.like(root.get("name").as(String.class), "%" + condition.getName() + "%"));

            // 电话号码
            if (!StringUtils.isBlank(condition.getPhone()))
                predicates.add(cb.equal(root.get("phone").as(String.class), condition.getPhone()));

            // imei标识
            if (!StringUtils.isBlank(condition.getImei()))
                predicates.add(cb.equal(root.get("imei").as(String.class), condition.getImei()));

            //性别
            if (!StringUtils.isBlank(condition.getGender()) && !condition.getGender().equalsIgnoreCase("全部"))
                predicates.add(cb.equal(root.get("gender").as(String.class), condition.getGender()));

            // 服务状态
            // if (null != condition.getServiceStatus() && -1 != condition.getServiceStatus())
            // predicates.add(cb.equal(root.get("serviceStatus").as(Integer.class), condition.getServiceStatus()));

            // 加入条件
            query.where(predicates.toArray(new Predicate[predicates.size()]));
            return null;
        }, pageable);
    }

    /**
     * 手表用户绑定手表信息
     *
     * @param imei 手表IMEI
     * @return -1 此用户已经被其他app用户绑定 & 0 绑定失败 & 1 成功
     */
    @Transactional
    public Integer bindWatch(String imei, Integer appUserId) {
        // 通过imei查询用户信息
        User temp = findByImei(imei);

        // 通过app用户编号查询用户信息
        AppUser appUser = appUserService.findById(appUserId);

        if (null == temp) {
            // 用户信息不存在, 创建新的用户信息
            temp = create(imei);
            log.info("The User info not exists! Create it! imei = {}", imei);
        }
        // 绑定
        return appBindWatchService.bind(temp, appUser);
    }

    /**
     * 新增用户信息
     *
     * @return 用户信息
     */
    public User create() {
        return create(new User());
    }

    /**
     * 通过imei创建新用户
     *
     * @param imei imei
     * @return 用户信息
     */
    public User create(String imei) {
        return create(new User(imei));
    }

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 用户信息
     */
    public User create(User user) {
        return null == user ? null : userRepository.saveAndFlush(user);
    }

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return .
     */
    public User update(User user) {
        User temp = findById(user.getId());

        //<editor-fold desc="判断实体的每一个值, 不为NULL或空串的修改">

        // 性别
        if (StringUtils.isNotBlank(user.getGender())) {
            String gender = user.getGender().trim();
            if (2 == gender.length() && (gender.equalsIgnoreCase("男") || gender.equalsIgnoreCase("女")))
                temp.setGender(user.getGender());
        }

        // 血型
        if (StringUtils.isNotBlank(user.getBlood()) && 2 > user.getBlood().trim().length() &&
                (user.getBlood().trim().equalsIgnoreCase("a") || user.getBlood().trim().equalsIgnoreCase("b") ||
                        user.getBlood().trim().equalsIgnoreCase("o") || user.getBlood().trim().equalsIgnoreCase("ab")))
            temp.setBlood(user.getBlood());

        // 身高
        if (NumberUtils.isNumber(user.getHeight()))
            temp.setHeight(user.getHeight());

        // 体重
        if (NumberUtils.isNumber(user.getWeight()))
            temp.setWeight(user.getWeight());

        // 听力
        if (StringUtils.isNotBlank(user.getHearing())) {
            String hearing = user.getHearing();
            if (2 == hearing.length() && (hearing.equalsIgnoreCase("较差") || hearing.equalsIgnoreCase("一般") || hearing.equalsIgnoreCase("良好")))
                temp.setHearing(hearing);
        }

        // 视力
        if (StringUtils.isNotBlank(user.getVision())) {
            String vision = user.getHearing();
            if (2 == vision.length() && (vision.equalsIgnoreCase("较差") || vision.equalsIgnoreCase("一般") || vision.equalsIgnoreCase("良好")))
                temp.setHearing(vision);
        }

        // 记忆力
        if (StringUtils.isNotBlank(user.getMemory())) {
            String memory = user.getHearing();
            if (2 == memory.length() && (memory.equalsIgnoreCase("较差") || memory.equalsIgnoreCase("一般") || memory.equalsIgnoreCase("良好")))
                temp.setHearing(memory);
        }

        // 语言表达能力
        if (StringUtils.isNotBlank(user.getLanguage())) {
            String language = user.getHearing();
            if (2 == language.length() && (language.equalsIgnoreCase("较差") || language.equalsIgnoreCase("一般") || language.equalsIgnoreCase("良好")))
                temp.setHearing(language);
        }

        // 居住状况
        if (StringUtils.isNotBlank(user.getLiving())) {
            String living = user.getHearing();
            temp.setHearing(living);
        }

        // 生日
        if (null != user.getBirthday())
            temp.setBirthday(user.getBirthday());

        // 是否有社保
        if (StringUtils.isNotBlank(user.getMedicare())) {
            String medicare = user.getMedicare();
            if (1 == medicare.length() && (medicare.equalsIgnoreCase("是") || medicare.equalsIgnoreCase("否")))
                temp.setMedicare(medicare);
        }

        // 是否自理
        if (StringUtils.isNotBlank(user.getThemselves())) {
            String themselves = user.getMedicare();
            if (1 == themselves.length() && (themselves.equalsIgnoreCase("是") || themselves.equalsIgnoreCase("否")))
                temp.setMedicare(themselves);
        }

        // 过敏药物
        if (StringUtils.isNotBlank(user.getAllergyMedications()))
            temp.setAllergyMedications(user.getAllergyMedications());

        // 病史
        if (StringUtils.isNotBlank(user.getMedical()))
            temp.setMedical(user.getMedical());

        // 饮食习惯
        if (StringUtils.isNotBlank(user.getEatingHabits()))
            temp.setEatingHabits(user.getEatingHabits());

        // 民族
        if (StringUtils.isNotBlank(user.getNationality()))
            temp.setNationality(user.getNationality());

        // 称呼
        // if (StringUtils.isNotBlank(user.getNickName()))
        //     temp.setNickName(user.getNickName());

        // 详细住址
        if (StringUtils.isNotBlank(user.getAddress()))
            temp.setAddress(user.getAddress());

        // 备注
        if (StringUtils.isBlank(user.getRemark()))
            temp.setRemark(user.getRemark());

        // 身份证号码
        if (StringUtils.isNotBlank(user.getIdCard())) {
            String idCard = user.getIdCard();
            if ((18 == idCard.length() || 15 == idCard.length()) && ValidataUtils.isIDCard(idCard))
                temp.setIdCard(user.getIdCard());
        }

        // 手机号码
        if (StringUtils.isNotBlank(user.getPhone())) {
            String phone = user.getPhone();
            if (11 == phone.length() && ValidataUtils.isPhone(phone))
                temp.setPhone(user.getPhone());
        }

        // 爱好
        if (StringUtils.isNotBlank(user.getHobby()))
            temp.setHobby(user.getHobby());

        // 姓名
        if (StringUtils.isNotBlank(user.getName())) {
            String name = user.getName();
            if (10 > name.length())
                temp.setName(user.getName());
        }

        // 紧急联系人名称
        if (StringUtils.isNotBlank(user.getContactName())) {
            String conName = user.getContactName();
            if (10 > conName.length())
                temp.setContactName(conName);
        }

        // 紧急联系人手机号码
        if (StringUtils.isNotBlank(user.getContactPhone())) {
            String conPhone = user.getContactPhone();
            if (11 == conPhone.length() && ValidataUtils.isPhone(conPhone))
                temp.setContactPhone(user.getContactPhone());
        }

        //</editor-fold>

        return userRepository.save(calcUserInfo(temp));
    }


    /**
     * 通过用户信息及日期查询用户当天的健康状态
     *
     * @param user 用户
     * @param date 日期
     * @return 健康分析状态
     */
    public HealthAnalysis getUserHistory(User user, Date date) {
        return healthAnalysisService.findByUserAndDate(user, date, DateUtils.addDay(date, 1));
    }

    /**
     * 性别比例统计
     *
     * @return 性别比例
     */
    public Map<String, Object> sex_ratio_statistic(Map<String, Object> map) {

        // 查询数据库中男性用户的总人数
        long male = userRepository.findByGender("男");

        // 查询数据库中女性用户的总人数
        long female = userRepository.findByGender("女");

        //查询数据库中未知用户的总人数
        long unknown = userRepository.findByGender("未知");

        // 查询数据库中总用户数
        long count = userRepository.count();

        // 将浮点转换为百分比
        DecimalFormat nf = (DecimalFormat) NumberFormat.getPercentInstance();

        //00表示小数点2位
        nf.applyPattern("00%");

        //2表示精确到小数点后2位
        nf.setMaximumFractionDigits(2);

        // 将查询及计算的结果放到集合并返回
        map.put("male", male);                                                  // 男性人数
        map.put("female", female);                                              // 女性人数
        map.put("unknown", unknown);                                             //未知人数
        map.put("count", count);                                                // 总人数
        map.put("maleRatio", nf.format((double) male / (double) count));        // 男性占总人数的百分比
        map.put("femaleRatio", nf.format((double) female / (double) count));    // 女性占总人数的百分比
        map.put("unknownRatio", nf.format((double) unknown / (double) count));  // 未知占总人数的百分比
        map.put("countRatio", nf.format((double) male + (double) female + (double) unknown));
        return map;
    }

    /**
     * 各年龄段人数统计
     *
     * @return .
     */
    public Map<String, Object> age_statistic() {
        Map<String, Object> map = new HashMap<>();

        // 0 - 30岁
        long one = userRepository.findByAge(0, 30);
        map.put("one", one);

        // 30 - 40岁
        long two = userRepository.findByAge(30, 40);
        map.put("two", two);

        // 40 - 50岁
        long three = userRepository.findByAge(40, 50);
        map.put("three", three);

        // 50 - 60岁
        long four = userRepository.findByAge(50, 60);
        map.put("four", four);

        // 60 - 70岁
        long five = userRepository.findByAge(60, 70);
        map.put("five", five);

        // 70 - 80岁
        long six = userRepository.findByAge(70, 80);
        map.put("six", six);

        // 80 - 90岁
        long seven = userRepository.findByAge(80, 90);
        map.put("seven", seven);

        // 90 - 100 岁
        long eight = userRepository.findByAge(90, 100);
        map.put("eight", eight);

        // 100岁以上
        long nine = userRepository.findByAge(100, 1000);
        map.put("nine", nine);

//        long kong = userRepository.findByAge(-1,-100);
//        map.put("kong",kong);

        // 总人数
        long count = userRepository.count();
        map.put("count", count);

        return map;
    }

    /**
     * 推送心率信息给用户绑定的app用户
     *
     * @param id             用户编号
     * @param heartRateValue 信息测试值
     */
    public void pushHeartRateToAppUser(Integer id, Integer heartRateValue) {

        User user = findById(id);
        // 当前用户没有绑定app用户, 取消推送到手机
        if (null == user || null == user.getAppUserId()) return;

        // 通过app用户编号查询用户信息
        AppUser appUser = appUserService.findById(user.getAppUserId());

        // 推送信息
        appUserPush.pushHeartRate(appUser.getPhone(), heartRateValue);
    }

    /**
     * 获取手表相关信息
     *
     * @param imei   设备imei
     * @param option 操作
     * @return 结果
     */
    public SimpleData pullWatchInfo(String imei, String option) {
        return userPush.pullWatchInfo(imei, option);
    }

    /**
     * 指定手表设置闹钟
     *
     * @param imei   用户编号
     * @param extras 扩展内容
     * @return .
     */
    public SimpleData setClock(String imei, Map<String, String> extras) {
        return userPush.setClock(imei, extras);
    }

    /**
     * 修改用户位置信息
     *
     * @param id        用户编号
     * @param longitude 经度
     * @param latitude  纬度
     * @return .
     */
    @Transactional
    public Integer setLocation(Integer id, String longitude, String latitude) {
        return userRepository.updateLocation(longitude, latitude, id);
    }

    /**
     * 修改用户手表电量
     *
     * @param id      用户编号
     * @param battery 手表电量
     * @return .
     */
    @Transactional
    public Integer setBattery(Integer id, Integer battery) {

        // 指定手表电量值下降到一定程度时自动通知app用户
        if (30 == battery || 20 == battery || 15 == battery || 10 == battery ||
                5 == battery || 4 == battery || 3 == battery || 2 == battery || 1 == battery) {

            // 通过用户id获取用户信息
            User user = findById(id);

            // 判断用户是否绑定了app用户, 有则推送电量信息, 如无则略过
            if (null != user.getAppUserId())
                // 推送电量信息
                appUserPush.pushBattery(battery, user.getNickName(), user.getAppUserId().toString());
        }

        // 存储电量信息
        return userRepository.updateBattery(battery, id);
    }

    /**
     * 通过app用户编号查询用户信息
     *
     * @param appUserId app用户编号
     * @return 用户信息
     */
    public List<User> findByAppUserId(Integer appUserId) {
        return userRepository.findByAppUserId(appUserId);
    }

}