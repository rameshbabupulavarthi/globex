package com.globex.model.vo;

import com.globex.model.vo.pm.ApplicationDO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Sunil Golla on 4/11/2017.
 */
@Data
public class LocalBrokerInsuredContactDO implements Serializable{

    private Integer localBrokerInsuredContactId;

    private ApplicationDO application;

    private String country;

    private String contactName;

    private String address;

    private Integer phoneCountryCode;

    private Integer phoneAreaCode;

    private Long phone;

    private Integer phoneExtension;

    private Integer faxCountryCode;

    private Integer faxAreaCode;

    private Long fax;

    private String email;

    private int contactType;
}
