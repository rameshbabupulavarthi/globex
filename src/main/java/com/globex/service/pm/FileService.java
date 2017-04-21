package com.globex.service.pm;

import com.globex.model.entity.common.File;
import com.globex.model.vo.PageModel;
import com.globex.model.vo.pm.ApplicationDO;
import com.globex.model.vo.pm.FileInfoDO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Sunil Golla on 2/11/2017.
 */
public interface FileService {

    public PageModel<FileInfoDO> list(PageModel<FileInfoDO> pageModel);

    public void save(ApplicationDO applicationDO);
}
