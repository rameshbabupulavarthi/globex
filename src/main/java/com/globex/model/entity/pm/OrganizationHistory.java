package com.globex.model.entity.pm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Sunil Golla on 6/1/2017.
 */
@Data
@EqualsAndHashCode(of = {"historyId"})
@ToString(exclude={"organization"})
@Entity
@Table(name="org_history")
public class OrganizationHistory {

    @Id
    @Column(name = "historyId")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long historyId;

    @Column(name = "year")
    private Integer year;

    @Column(name = "premium")
    private String premium;

    @Column(name = "premiumCurrency")
    private String premiumCurrency;

    @Column(name = "combinedRatio")
    private String combinedRatio;

    @Column(name = "totalAssets")
    private String totalAssets;

    @Column(name = "totalAssetsCurrency")
    private String totalAssetsCurrency;

    @Column(name = "finAttachment")
    private String finAttachment;

    @Column(name = "finComments")
    private String finComments;

    @Column(name = "ranking")
    private String ranking;

    @Column(name = "type")
    private Integer type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID")
    private Organization organization;







}
