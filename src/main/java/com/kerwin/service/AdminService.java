package com.kerwin.service;

import com.kerwin.model.Admin;
import com.kerwin.repository.AdminRepository;
import com.kerwin.utils.IPUtils;
import com.kerwin.utils.PasswordUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Tiamo on 2016/4/22.
 * Service implements by Admin
 */
@Service
public class AdminService {
    private static final Logger log = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private AdminRepository adminRepository;

    /**
     * 管理员登录
     *
     * @param admin 管理员 (name & password)
     * @return 管理员编号
     */
    public Admin login(HttpServletRequest request, Admin admin) {
        Admin temp = null;
        if (null != admin && !StringUtils.isBlank(admin.getName()) && !StringUtils.isBlank(admin.getPassword())) {
            temp = findByName(admin.getName());
            if (null != temp && PasswordUtils.checkPassword(temp.getPassword(), admin.getPassword(), temp.getLastLoginTime().toString())) {
                temp = setAdminLoginSuccess(request, admin.getPassword(), temp);
            }
        }
        return temp;
    }

    /**
     * 管理员增加
     *
     * @param request request
     * @param admin   管理员信息
     * @return -2 => 用户名已经存在 & -1 => 数据不完整 & 0 => 失败 & 1 => 成功
     */
    public Integer addAdmin(HttpServletRequest request, Admin admin) {
        if (null != findByName(admin.getName())) return -2;
        admin = adminRepository.save(setAdminLoginSuccess(request, admin.getPassword(), admin));
        return null == admin ? 0 : 1;
    }

    /**
     * 通过ID查询管理员信息
     *
     * @param name 管理员编号
     * @return 管理员信息
     */
    private Admin findByName(String name) {
        return adminRepository.findByName(name);
    }

    /**
     * 修改管理员登录时间等信息
     *
     * @param request  request请求
     * @param password 密码
     * @param admin    管理员信息
     * @return 修改后的管理员信息
     */
    private Admin setAdminLoginSuccess(HttpServletRequest request, String password, Admin admin) {
        admin.setLastLoginIP(IPUtils.getIpAddress(request));
        admin.setLoginCount(null == admin.getLoginCount() ? 0 : admin.getLoginCount() + 1);
        admin.setLastLoginTime(new DateTime());
        admin.setPassword(PasswordUtils.md5(password, admin.getLastLoginTime().toString(), false));
        return adminRepository.save(admin);
    }

    /**
     * 查询全部用户的信息
     *
     * @param name     用户名
     * @param pageable 分页信息
     * @return 用户信息
     */
    public Page<Admin> findAll(String name, Pageable pageable) {
        int page = pageable.getPageNumber();
        System.err.println(page);
        if (0 != page)
            pageable = new PageRequest(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
        return adminRepository.findAll((root, query, cb) -> {
            if (!StringUtils.isBlank(name))
                query.where(cb.equal(root.get("name").as(String.class), name));
            return null;
        }, pageable);
    }
}
