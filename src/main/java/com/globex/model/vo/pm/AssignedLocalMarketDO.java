package com.globex.model.vo.pm;

import com.globex.model.entity.pm.AssignedLocalMarket;
import lombok.Data;

/**
 * Created by Sunil Golla on 2/10/2017.
 */
@Data
public class AssignedLocalMarketDO {

    private Integer id;

    private ApplicationDO application;

    private OrganizationDO organization;

    private Integer country;

    public AssignedLocalMarketDO(){

    }

    public AssignedLocalMarketDO(AssignedLocalMarket assignedLocalMarket){
        this.id=application.getId();
        this.country=assignedLocalMarket.getCountry();
    }

}
