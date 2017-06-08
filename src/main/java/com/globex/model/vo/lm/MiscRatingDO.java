package com.globex.model.vo.lm;

import com.globex.model.entity.partner.MiscRating;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Sunil Golla on 6/4/2017.
 */
@Data
public class MiscRatingDO implements Serializable {

    private Long miscId;

    private String companyName;

    private String companyCountry;

    private String companyWebsite;

    private String companyRating;

    private String companyOutlook;

    private String companyAttachment;

    public MiscRatingDO(){

    }

    public MiscRatingDO(MiscRating miscRating){
        companyName=miscRating.getCompanyName();
        companyCountry=miscRating.getCompanyCountry();
        companyWebsite=miscRating.getCompanyWebsite();
        companyRating=miscRating.getCompanyRating();
        companyOutlook=miscRating.getCompanyOutlook();
        companyAttachment=miscRating.getCompanyAttachment();
    }

    public MiscRating value(){
        MiscRating miscRating=new MiscRating();
        miscRating.setCompanyName(companyName);
        miscRating.setCompanyCountry(companyCountry);
        miscRating.setCompanyWebsite(companyWebsite);
        miscRating.setCompanyRating(companyRating);
        miscRating.setCompanyOutlook(companyOutlook);
        miscRating.setCompanyAttachment(companyAttachment);
        return miscRating;
    }


}
