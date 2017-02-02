package com.globex.model.vo;

import com.globex.model.entity.partner.NetworkDetails;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sunil Golla on 12/31/2016.
 */
@Data
public class NetworkDetailsDO implements Serializable {

    private Long id;

    private int networkPartnerQualifier;

    private int networkPartnerQuaLob;

    private String generalComments;

    private int networkPartnerScore;

    private String averageResponseTime;

    private String primaryContactComments;

    private int vettingStatus;

    private String vettingStatusComments;

    private Date vettingDate;

    private Date vettingReminder;

    private String vettingReminderComments;

    public NetworkDetailsDO(){

    }

    public NetworkDetailsDO (NetworkDetails networkDetails){
        id=networkDetails.getId();
        networkPartnerQualifier= networkDetails.getNetworkPartnerQualifier();
        networkPartnerQuaLob=networkDetails.getNetworkPartnerQuaLob();
        generalComments=networkDetails.getGeneralComments();
        networkPartnerScore=networkDetails.getNetworkPartnerScore();
        averageResponseTime=networkDetails.getAverageResponseTime();
        primaryContactComments=networkDetails.getPrimaryContactComments();
        vettingStatus=networkDetails.getVettingStatus();
        vettingStatusComments=networkDetails.getVettingStatusComments();
        vettingDate= networkDetails.getVettingDate();
        vettingReminder=networkDetails.getVettingReminder();
        vettingReminderComments=networkDetails.getVettingReminderComments();
    }


    public NetworkDetails getValue(){
        NetworkDetails networkDetails=new NetworkDetails();
        networkDetails.setId(networkDetails.getId());
        networkDetails.setNetworkPartnerQualifier(networkPartnerQualifier);
        networkDetails.setNetworkPartnerQuaLob(networkPartnerQuaLob);
        networkDetails.setGeneralComments(generalComments);
        networkDetails.setNetworkPartnerScore(networkPartnerScore);
        networkDetails.setAverageResponseTime(averageResponseTime);
        networkDetails.setPrimaryContactComments(primaryContactComments);
        networkDetails.setVettingStatus(vettingStatus);
        networkDetails.setVettingStatusComments(vettingStatusComments);
        networkDetails.setVettingDate(vettingDate);
        networkDetails.setVettingReminder(vettingReminder);
        networkDetails.setVettingReminderComments(vettingReminderComments);
        return networkDetails;
    }
}
