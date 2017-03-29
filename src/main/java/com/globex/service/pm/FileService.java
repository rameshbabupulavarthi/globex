package com.globex.service.pm;

import com.globex.model.entity.common.File;
import com.globex.model.vo.pm.FileInfoDO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Sunil Golla on 2/11/2017.
 */
public interface FileService {

    public Page<File> list(Integer pageNumber,Integer pageSize);
}
