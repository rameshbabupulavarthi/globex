package com.globex.model.vo.lm;

import com.globex.model.entity.lm.UWDepDetails;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Sunil Golla on 6/4/2017.
 */
@Data
public class UWDepDetailsDO implements Serializable {

    private Long uwId;

    private String uwLob;

    private String uwName;

    private String uwRole;

    private String uwComments;

    private String uwOffice;

    private String uwGsm;

    private String uwEmail;

    public UWDepDetailsDO(UWDepDetails uwDepDetails){
        uwId=uwDepDetails.getUwId();
        uwLob=uwDepDetails.getUwLob();
        uwName=uwDepDetails.getUwName();
        uwRole=uwDepDetails.getUwRole();
        uwComments=uwDepDetails.getUwComments();
        uwOffice=uwDepDetails.getUwOffice();
        uwGsm=uwDepDetails.getUwGsm();
        uwEmail=uwDepDetails.getUwEmail();
    }

    public UWDepDetails value(){
        UWDepDetails uwDepDetails=new UWDepDetails();
        uwDepDetails.setUwId(uwId);
        uwDepDetails.setUwLob(uwLob);
        uwDepDetails.setUwName(uwName);
        uwDepDetails.setUwRole(uwRole);
        uwDepDetails.setUwComments(uwComments);
        uwDepDetails.setUwOffice(uwOffice);
        uwDepDetails.setUwGsm(uwGsm);
        uwDepDetails.setUwEmail(uwEmail);
        return uwDepDetails;
    }

}
