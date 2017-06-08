package com.globex.model.entity.partner;

import com.globex.model.entity.pm.Organization;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Sunil Golla on 6/3/2017.
 */
@Data
@EqualsAndHashCode(of = {"commReqId"})
@ToString(exclude={"organization"})
@Entity
@Table(name="comm_req")
public class CommissionRequirement {

    @Id
    @Column(name = "REQ_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long commReqId;

    @Column(name = "commissionLob")
    private String commissionLob;

    @Column(name = "commissionRate")
    private Double commissionRate;

    @Column(name = "commissionAppliedTo")
    private String commissionAppliedTo;

    @Column(name = "commissionFlatAmount")
    private Double commissionFlatAmount;

    @Column(name = "commissionCurrency")
    private String commissionCurrency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID")
    private Organization organization;

}
