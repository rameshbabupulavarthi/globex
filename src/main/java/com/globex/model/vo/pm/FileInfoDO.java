package com.globex.model.vo.pm;

import com.globex.model.entity.common.Application;
import com.globex.model.entity.common.File;
import com.globex.model.entity.common.FileAttachment;
import com.globex.model.vo.FileAttachmentDO;
import com.globex.model.vo.OrganizationDO;
import com.globex.model.vo.UserDO;
import com.utils.DateUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sunil Golla on 2/10/2017.
 */
@Data
public class FileInfoDO implements Serializable {

    private Long fileId;

    private OrganizationDO organization;

    private UserDO forwardTo;

    private UserDO updatedBy;

    private UserDO createdBy;

    private int fileStatus;

    private Integer prospect;

    private String dateCreated;

    private String dateUpdated;

    private Set<ApplicationDO> applications;

    private Set<FileAttachmentDO> fileAttachments;

    public FileInfoDO(){

    }


    public FileInfoDO(File fileInfo){
        this.fileId=fileInfo.getFileId();
        this.organization=new OrganizationDO(fileInfo.getOrganization());
        this.forwardTo=fileInfo.getForwardTo()!=null?new UserDO(fileInfo.getForwardTo()):null;
        this.fileStatus=fileInfo.getFileStatus();
        this.prospect=fileInfo.getProspect();
        this.createdBy=fileInfo.getCreatedBy()!=null?new UserDO(fileInfo.getCreatedBy()):null;
        this.dateCreated=DateUtil.formatDate(fileInfo.getDateCreated());
        this.updatedBy=fileInfo.getUpdatedBy()!=null?new UserDO(fileInfo.getUpdatedBy()):null;
        this.dateUpdated=DateUtil.formatDate(fileInfo.getDateUpdated());
        this.applications=getApplications(fileInfo.getApplications());
        this.fileAttachments=getFileAttachments(fileInfo.getFileAttachments());
    }

    public Set<ApplicationDO> getApplications(Set<Application> applications){
        if(applications!=null && !applications.isEmpty()){
            Set<ApplicationDO> applicationDOs=new HashSet<ApplicationDO>();
            for(Application application:applications){
                applicationDOs.add(new ApplicationDO(application));
            }
            return applicationDOs;
        }
        return null;
    }

    public Set<FileAttachmentDO> getFileAttachments(Set<FileAttachment> fileAttachments){
        if(fileAttachments!=null && !fileAttachments.isEmpty()){
            Set<FileAttachmentDO> fileAttachmentDOs=new HashSet<FileAttachmentDO>();
            for(FileAttachment fileAttachment:fileAttachments){
                fileAttachmentDOs.add(new FileAttachmentDO(fileAttachment));
            }
            return fileAttachmentDOs;
        }
        return null;
    }
}
