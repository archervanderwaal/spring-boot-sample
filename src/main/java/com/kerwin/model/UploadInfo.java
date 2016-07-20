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
 * Created by Kerwin on 2016/5/18.
 * File upload
 */
@Entity
@Table(name = "m_upload")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadInfo implements Serializable {
    private static final long serialVersionUID = 4386500589906329053L;

    /**
     * 命名空间
     */
    @Id
    @Column(name = "upload_namespace", length = 20)
    String namespace;

    /**
     * ak
     */
    @Column(name = "upload_ak", length = 8)
    String ak;

    /**
     * sk
     */
    @Column(name = "upload_sk", length = 50)
    String sk;
}
