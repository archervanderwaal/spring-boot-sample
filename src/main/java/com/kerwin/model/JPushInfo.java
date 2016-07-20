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
 * JPush Info
 */
@Entity
@Table(name = "m_push")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JPushInfo implements Serializable {
    private static final long serialVersionUID = -6271970670174855725L;

    /**
     * 名称 如：App / Watch
     */
    @Id
    @Column(name = "push_name", length = 10)
    String name;

    /**
     * AppKey
     */
    @Column(name = "push_app_key", length = 100)
    String appKey;

    /**
     * MasterSecret
     */
    @Column(name = "push_master_secret", length = 100)
    String masterSecret;
}
