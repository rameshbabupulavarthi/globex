package com.globex.model.vo.pm;

import com.globex.model.entity.common.Application;
import com.globex.model.entity.common.File;
import com.globex.model.vo.OrganizationDO;
import com.globex.model.vo.UserDO;
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

    /*private Date dateCreated;

    private Date dateUpdated;*/

    private Set<ApplicationDO> applications;

    public FileInfoDO(){

    }


    public FileInfoDO(File fileInfo){
        this.fileId=fileInfo.getFileId();
        this.organization=new OrganizationDO(fileInfo.getOrganization());
        this.forwardTo=fileInfo.getForwardTo()!=null?new UserDO(fileInfo.getForwardTo()):null;
        this.fileStatus=fileInfo.getFileStatus();
        this.prospect=fileInfo.getProspect();
        this.createdBy=fileInfo.getCreatedBy()!=null?new UserDO(fileInfo.getCreatedBy()):null;
        //this.dateCreated=fileInfo.getDateCreated();
        this.updatedBy=fileInfo.getUpdatedBy()!=null?new UserDO(fileInfo.getUpdatedBy()):null;
        //this.dateUpdated=fileInfo.getDateUpdated();
        this.applications=getApplications(fileInfo.getApplications());
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

}
