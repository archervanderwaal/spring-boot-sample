package com.kerwin.repository;

import com.kerwin.model.UploadInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Kerwin on 2016/5/18.
 * Upload repository
 */
public interface UploadInfoRepository extends JpaRepository<UploadInfo, String>, JpaSpecificationExecutor<UploadInfo> {
}
