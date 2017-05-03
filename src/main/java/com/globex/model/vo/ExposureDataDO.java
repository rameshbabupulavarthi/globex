package com.globex.model.vo;

import com.globex.model.entity.common.ExposureData;
import com.globex.model.vo.pm.ApplicationDO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Sunil Golla on 4/11/2017.
 */
@Data
public class ExposureDataDO implements Serializable {

    private Integer exposureDataId;

    private ApplicationDO application;

    private String country;

    private Double exposure;

    private Double rate;

    private String states;

    public ExposureDataDO(){

    }

    public ExposureDataDO(ExposureData exposureData){
        this.country= exposureData.getCountry();
        this.states=exposureData.getStates();
        this.exposure=exposureData.getExposure();
        this.rate=exposureData.getRate();
    }

    public ExposureData value(){
        ExposureData exposureData=new ExposureData();
        exposureData.setCountry(this.getCountry());
        exposureData.setStates(this.getStates());
        exposureData.setExposure(this.getExposure());
        exposureData.setRate(this.getRate());
        return exposureData;
    }
}
