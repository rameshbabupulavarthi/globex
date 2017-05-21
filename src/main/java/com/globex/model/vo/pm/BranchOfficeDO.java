package com.globex.model.vo.pm;

import com.globex.model.entity.pm.BranchOffice;
import com.globex.model.entity.pm.Organization;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Sunil Golla on 5/17/2017.
 */
@Data
public class BranchOfficeDO implements Serializable {

    private Integer branchOfficeId;

    private String branchCountry;

    private String branchAddress;

    public BranchOfficeDO(){

    }

    public BranchOfficeDO(BranchOffice branchOffice){
        this.branchOfficeId=branchOffice.getBranchOfficeId();
        this.branchCountry=branchOffice.getBranchCountry();
        this.branchAddress=branchOffice.getBranchAddress();
    }

    public BranchOffice value(){
        BranchOffice branchOffice=new BranchOffice();
        branchOffice.setBranchOfficeId(this.getBranchOfficeId());
        branchOffice.setBranchCountry(this.getBranchCountry());
        branchOffice.setBranchAddress(this.getBranchAddress());
        return branchOffice;
    }
}
