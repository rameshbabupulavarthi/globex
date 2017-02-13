package com.globex.model.entity.pm;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Sunil Golla on 2/10/2017.
 */
@Data
@Entity
@Table(name="organization")
public class Organization {

    @Id
    @Column(name="ORGANIZATION_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name="ORGANIZATION_NAME")
    private String orgName;

    @Column(name="REGISTRATION_DATE")
    private Timestamp regDate;

    @Column(name="ADDRESS_LINE_1")
    private String address1;

    @Column(name="ADDRESS_LINE_2")
    private String address2;

    @Column(name="CITY")
    private String city;

    @Column(name="STATE")
    private String state;

    @Column(name="COUNTRY")
    private String country;

    @Column(name="ZIP")
    private String zip;

    @Column(name="WEB_SITE")
    private String website;

    @Column(name="ORGANIZATION_TYPE")
    private Integer orgType;

    @Column(name="PARENT_ORGANIZATION_ID")
    private Integer parentOrgId;

    @Column(name="APPROVED")
    private Integer approved;

    @Column(name="COMMENT")
    private String comment;

    @Column(name="LICENSED_STATE")
    private String licenceState;

}
