package com.kerwin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Kerwin on 2016/4/25.
 * 健康百科
 */
@Entity
@Table(name = "m_ency")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ency implements Serializable {

    private static final long serialVersionUID = -7123603782011315175L;
    /**
     * 百科编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "ency_id")
    Long id;

    /**
     * 百科类型
     */
    @JoinColumn(name = "ency_type")
    String type;

    /**
     * 百科标题
     */
    @Column(name = "ency_title", length = 30)
    String title;

    /**
     * 百科内容
     */
    @Column(name = "ency_content")
    String content;
}