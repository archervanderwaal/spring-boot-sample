package com.kerwin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Kerwin on 2016/4/30.
 * Message Info
 */
@Entity
@Table(name = "m_message")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageInfo implements Serializable {
    private static final long serialVersionUID = -6540967748660462154L;
    /**
     * 名称
     */
    @Id
    @Column(name = "message_name", length = 20)
    String name;

    /**
     * 链接地址
     */
    @Column(name = "message_url", length = 100)
    String url;

    /**
     * UserId
     */
    @Column(name = "message_user", length = 5)
    String userId;

    /**
     * 用户名
     */
    @Column(name = "message_account", length = 10)
    String account;

    /**
     * 密码
     */
    @Column(name = "message_password", length = 30)
    String password;

    /**
     * 内容
     */
    @Column(name = "message_content")
    String content;
}
