package com.globex.model.vo.pm;

import com.globex.model.entity.pm.FileInfo;
import com.globex.model.vo.UserDO;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Sunil Golla on 2/10/2017.
 */
@Data
public class FileInfoDO implements Serializable {

    private Integer id ;

    //private Organization partnerMarketId;
    private OrganizationDO organization;

    private Integer forwardTo ;

    private Integer fileStatus ;

    private Integer prospect;

    private UserDO createdBy;

    private Timestamp dateCreated;

    private Integer updatedBy;

    private Timestamp dateUpdated;

    private ApplicationDO application;

    public FileInfoDO(){

    }


    public FileInfoDO(FileInfo fileInfo){
        this.id=fileInfo.getId();
        this.organization=new OrganizationDO(fileInfo.getOrganization());
        this.forwardTo=fileInfo.getForwardTo();
        this.fileStatus=fileInfo.getFileStatus();
        this.prospect=fileInfo.getProspect();
        this.createdBy=new UserDO(fileInfo.getUser());
        this.dateCreated=fileInfo.getDateCreated();
        this.updatedBy=fileInfo.getUpdatedBy();
        this.dateUpdated=fileInfo.getDateUpdated();
        this.application=new ApplicationDO(fileInfo.getApplication());
    }

}
