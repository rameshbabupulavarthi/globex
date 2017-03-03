package com.globex.service.pm;

import com.globex.model.entity.pm.FileInfo;
import com.globex.model.vo.pm.FileInfoDO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Sunil Golla on 2/11/2017.
 */
public interface FileService {

    public Page<FileInfo> list(Integer pageNumber,Integer pageSize);
}
