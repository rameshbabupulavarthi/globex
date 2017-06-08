package com.globex.model.vo.lm;

import com.globex.model.entity.pm.OrganizationHistory;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Sunil Golla on 6/4/2017.
 */
@Data
public class OrganizationHistoryDO implements Serializable{

    private Long historyId;

    private Integer year;

    private String premium;

    private String premiumCurrency;

    private String combinedRatio;

    private String totalAssets;

    private String totalAssetsCurrency;

    private String finAttachment;

    private String finComments;

    private String ranking;

    private Integer type;

    public OrganizationHistoryDO(OrganizationHistory organizationHistory){
        historyId=organizationHistory.getHistoryId();
        year=organizationHistory.getYear();
        premium=organizationHistory.getPremium();
        premiumCurrency=organizationHistory.getPremiumCurrency();
        totalAssets=organizationHistory.getTotalAssets();
        totalAssetsCurrency=organizationHistory.getTotalAssetsCurrency();
        finAttachment=organizationHistory.getFinAttachment();
        finComments=organizationHistory.getFinComments();
        ranking=organizationHistory.getRanking();
        type=organizationHistory.getType();
    }

    public OrganizationHistory value(){
        OrganizationHistory organizationHistory=new OrganizationHistory();
        organizationHistory.setHistoryId(historyId);
        organizationHistory.setYear(year);
        organizationHistory.setPremium(premium);
        organizationHistory.setPremiumCurrency(premiumCurrency);
        organizationHistory.setTotalAssets(totalAssets);
        organizationHistory.setTotalAssetsCurrency(totalAssetsCurrency);
        organizationHistory.setFinAttachment(finAttachment);
        organizationHistory.setFinComments(finComments);
        organizationHistory.setRanking(ranking);
        organizationHistory.setType(type);
        return organizationHistory;
    }
}
