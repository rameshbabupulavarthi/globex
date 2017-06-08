package com.globex.model.entity.lm;

import com.globex.model.entity.pm.Organization;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sunil Golla on 6/6/2017.
 */
@Data
@EqualsAndHashCode(of = {"uwId"})
@ToString(exclude={"organization"})
@Entity
@Table(name="uw_dep_det")
public class UWDepDetails implements Serializable {

    @Id
    @Column(name = "uwId")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long uwId;

    @Column(name = "uwLob")
    private String uwLob;

    @Column(name = "uwName")
    private String uwName;

    @Column(name = "uwRole")
    private String uwRole;

    @Column(name = "uwComments")
    private String uwComments;

    @Column(name = "uwOffice")
    private String uwOffice;

    @Column(name = "uwGsm")
    private String uwGsm;

    @Column(name = "uwEmail")
    private String uwEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID")
    private Organization organization;
}
