package com.kerwin.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kerwin on 2016/5/23.
 * api model
 */
@Entity
@Table(name = "m_api_model")
@Data
@RequiredArgsConstructor
public class ApiModel implements Serializable {
    private static final long serialVersionUID = -1738629222941862544L;

    /**
     * 编号
     */
    @Id
    @Column(name = "api_id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    Integer id;

    /**
     * 主链接
     */
    @Column(name = "api_main_url", length = 20)
    String mainUrl;

    /**
     * 子链接
     */
    @Column(name = "api_child_url", length = 50)
    String childUrl;

    /**
     * 所属终端
     * Watch / App(Android / iOS)
     */
    @Column(name = "api_client", length = 20)
    String client;

    /**
     * 必填参数
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "apiModel")
    List<ApiParam> params = new ArrayList<>();
}
