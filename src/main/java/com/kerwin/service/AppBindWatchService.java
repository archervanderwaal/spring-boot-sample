package com.kerwin.service;

import com.kerwin.model.AppBindWatch;
import com.kerwin.model.AppUser;
import com.kerwin.model.User;
import com.kerwin.repository.AppBindWatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Kerwin on 2016/5/28.
 * app user and watch user bind info service
 */
@Service
public class AppBindWatchService {
    @Autowired
    private AppBindWatchRepository appBindWatchRepository;

    /**
     * 绑定手表用户与手机用户
     *
     * @param user    watch用户信息
     * @param appUser app用户信息
     * @return 结果
     */
    public Integer bind(User user, AppUser appUser) {
        // 判断当前用户与手表用户是否绑定
        if (null != findByUserAndAppUser(user, appUser))
            return -2;

        // 绑定手表用户与手机用户
        AppBindWatch bind = new AppBindWatch();
        bind.setUser(user);
        bind.setAppUser(appUser);
        bind.setDate(new Date());

        // 保存绑定信息
        bind = appBindWatchRepository.save(bind);

        // 判断是否绑定成功
        return null == bind ? 0 : 1;
    }

    /**
     * 解绑
     *
     * @param user    watch用户信息
     * @param appUser app用户信息
     * @return 结果
     */
    public Integer unBind(User user, AppUser appUser) {
        return 0;
    }

    /**
     * 通过用户信息查询绑定与被绑定信息
     *
     * @param user 用户信息(watch用户)
     * @return 绑定与被绑定信息
     */
    public List<AppBindWatch> findByUser(User user) {
        return appBindWatchRepository.findByUser(user);
    }

    /**
     * 通过用户信息查询绑定与被绑定信息
     *
     * @param appUser 用户信息(app用户)
     * @return 绑定与被绑定信息
     */
    public List<AppBindWatch> findByAppUser(AppUser appUser) {
        return appBindWatchRepository.findByAppUser(appUser);
    }

    /**
     * 通过手表用户信息与app用户信息查询绑定信息
     *
     * @param user    watch用户信息
     * @param appUser app用户信息
     * @return 绑定信息
     */
    public AppBindWatch findByUserAndAppUser(User user, AppUser appUser) {
        return appBindWatchRepository.findByUserAndAppUser(user, appUser);
    }
}
