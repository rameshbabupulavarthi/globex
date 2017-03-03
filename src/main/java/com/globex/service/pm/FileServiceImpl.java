package com.globex.service.pm;

import com.globex.model.entity.pm.FileInfo;
import com.globex.model.vo.pm.FileInfoDO;
import com.globex.repository.rdbms.pm.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sunil Golla on 2/11/2017.
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileRepository fileRepository;

    public Page<FileInfo> list(Integer pageNumber,Integer pageSize){
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Pageable limit = (Pageable) new PageRequest(pageNumber, pageSize,sort);
        Page<FileInfo> fileInfoPage =fileRepository.findAll(limit);
        return fileInfoPage;
    }
}
