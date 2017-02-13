package com.globex.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Sunil Golla on 2/10/2017.
 */
@Data
public class FileInfoDO implements Serializable {

    private Long fileId;
    private Long applicationId;
    private String applicationNo;
    private String partnerMarketName;
    private String insuredCompany;
    private Long fileStatus;
    private String dateCreated;
    private String policyEndDate;
    private boolean userCanShare;
    private String coverage;

}
