package com.globex.model.vo;

import com.globex.model.entity.common.Attachment;
import com.utils.DateUtil;
import lombok.Data;

/**
 * Created by Sunil Golla on 5/18/2017.
 */
@Data
public class AttachmentDO {

    private Integer attachmentId;

/*    private File file;*/

    private OrganizationDO organization;

    private String fileName;

    private String subject;

    private String description;

	private String dateCreated;

	private String dateSent;

    public AttachmentDO(Attachment attachment){
        this.setAttachmentId(attachment.getAttachmentId());
        this.setFileName(attachment.getFileName());
        this.setSubject(attachment.getSubject());
        this.setDescription(attachment.getDescription());
    }

    public Attachment value(){
        Attachment attachment=new Attachment();
        attachment.setFileName(fileName);
        attachment.setSubject(subject);
        attachment.setDescription(description);
        /*attachment.setDateCreated(DateUtil.getTimestamp(dateCreated));
        attachment.setDateSent(DateUtil.getTimestamp(dateSent));*/
        return attachment;
    }
}
