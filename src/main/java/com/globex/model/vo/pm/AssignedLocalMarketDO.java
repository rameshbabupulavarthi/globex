package com.globex.model.vo.pm;

import com.globex.model.entity.common.AssignedLocalMarket;
import com.globex.model.vo.OrganizationDO;
import lombok.Data;

/**
 * Created by Sunil Golla on 2/10/2017.
 */
@Data
public class AssignedLocalMarketDO {

    private Integer id;

    private ApplicationDO application;

    private OrganizationDO organization;

    private String country;

    public AssignedLocalMarketDO(){

    }

    public AssignedLocalMarketDO(AssignedLocalMarket assignedLocalMarket){
        this.id=application.getApplicationId();
        this.country=assignedLocalMarket.getCountry();
    }

}
