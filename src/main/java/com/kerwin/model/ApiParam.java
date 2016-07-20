package com.kerwin.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Kerwin on 2016/5/23.
 * api param
 */
@Entity
@Table(name = "m_api_param")
@Data
@RequiredArgsConstructor
public class ApiParam implements Serializable {
    private static final long serialVersionUID = -8660461154812391589L;

    /**
     * 参数编号
     */
    @Id
    @Column(name = "param_id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    Integer id;

    /**
     * 参数类型
     */
    @Column(name = "param_type")
    String paramType;

    /**
     * 参数名称
     */
    @Column(name = "param_name")
    String paramName;

    /**
     * 参数描述
     */
    @Column(name = "param_desc")
    String paramDesc;

    /**
     * 是否必填
     */
    @Column(name = "param_require")
    Boolean isRequire;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "api_model")
    ApiModel apiModel;
}
