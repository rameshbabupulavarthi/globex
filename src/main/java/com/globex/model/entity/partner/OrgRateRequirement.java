package com.globex.model.entity.partner;

import com.globex.model.entity.pm.Organization;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Sunil Golla on 6/4/2017.
 */
@Data
@EqualsAndHashCode(of = {"requirementId"})
@ToString(exclude={"organization"})
@Entity
@Table(name="org_rate_req")
public class OrgRateRequirement {

    @Id
    @Column(name = "REQ_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long requirementId;

    @Column(name="REQUIREMENT_NAME")
    private String requirementName;

    @Column(name="LOB")
    private String lob;

    @Column(name="RATE")
    private String rate;

    @Column(name="APPLIED_TO")
    private String appliedTo;

    @Column(name="MIN_PREMIUM")
    private String minPremium;

    @Column(name="CURRENCY")
    private String currency;

    @Column(name="REQ_TYPE")
    private String reqType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID")
    private Organization organization;
}
