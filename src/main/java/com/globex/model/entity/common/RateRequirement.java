package com.globex.model.entity.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sunil Golla on 5/24/2017.
 */
@Data
@EqualsAndHashCode(exclude = {"country"})
@ToString(exclude = {"country"})
@Entity
@Table(name = "rate_requirement")
public class RateRequirement implements Serializable {

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
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;
}
