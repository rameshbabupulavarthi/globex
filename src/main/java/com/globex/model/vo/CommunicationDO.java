package com.globex.model.vo;

import com.globex.model.entity.common.Communication;
import com.globex.model.vo.pm.*;
import com.utils.DateUtil;
import lombok.Data;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sunil Golla on 3/12/2017.
 */
@Data
public class CommunicationDO implements Serializable{

    private Long id;

    private FileInfoDO file;

    private OrganizationDO organizationByFromOrganizationId;

    private OrganizationDO organizationByToOrganizationId;

    private UserDO user;

    private String subject;

    private String content;

    private String fileAttachment;

    private boolean showToGlobex;

    private boolean showToPm;

    private boolean showToLm;

    private boolean replySent;

    private String country;

    private CommonsMultipartFile attachment;

    private Long fileId;

    private String dateCreated;

    private String replyExpectedBy;

    public CommunicationDO(){

    }

    public CommunicationDO(Communication communication){
        this.id=communication.getId();
        //this.file=communication.getFile()!=null?new FileInfoDO(communication.getFile()):null;
        this.organizationByFromOrganizationId=communication.getOrganizationByFromOrganizationId()!=null?new OrganizationDO(communication.getOrganizationByFromOrganizationId()):null;
        this.organizationByToOrganizationId=communication.getOrganizationByToOrganizationId()!=null?new OrganizationDO(communication.getOrganizationByToOrganizationId()):null;
        this.user=communication.getUser()!=null?new UserDO(communication.getUser()):null;
        this.subject=communication.getSubject();
        this.content=communication.getContent();
        this.fileAttachment=communication.getFileAttachment();
        this.showToGlobex=communication.isShowToGlobex();
        this.showToPm=communication.isShowToPm();
        this.showToLm=communication.isShowToLm();
        this.replySent=communication.isReplySent();
        this.country=communication.getCountry();
        /*this.dateCreated= DateUtil.formatDate(communication.getDateCreated());
        this.replyExpectedBy=DateUtil.formatDate(communication.getReplyExpectedBy());*/
    }
}
