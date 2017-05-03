package com.globex.model.vo;

import com.globex.model.entity.common.FileAttachment;
import com.globex.model.vo.pm.FileInfoDO;
import lombok.Data;

/**
 * Created by Sunil Golla on 5/3/2017.
 */
@Data
public class FileAttachmentDO {

    private Integer fileAttachmentId;

    private FileInfoDO file;

    private String fileName;

    public FileAttachmentDO(){

    }

    public FileAttachmentDO(FileAttachment fileAttachment){
        this.setFileName(fileAttachment.getFileName());
        this.setFileAttachmentId(fileAttachment.getFileAttachmentId());
    }
}
