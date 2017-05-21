package com.globex.model.entity.pm;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Sunil Golla on 5/17/2017.
 */
@Data
@EqualsAndHashCode(exclude = {"organization"})
@Entity
@Table(name = "coverage_contact")
public class CoverageContact implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "COVERAGE_CONTACT_ID")
    private Long coverageContactId;

    @Column(name = "COVERAGE_AREA")
    private String coverageArea;

    @Column(name = "CONTACT_NAME")
    private String contactName;

    @Column(name = "CONTACT_PHONE")
    private String contactPhone;

    @Column(name = "CONTACT_EMAIL")
    private String contactEmail;

    @Column(name = "CONTACT_POSITION")
    private String contactPosition;

    /*@OneToOne(cascade = { CascadeType.ALL,CascadeType.PERSIST})
    @JoinColumn(name = "CONTACT_INFO_ID")
    private ContactInfo contactInfo;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID")
    private Organization organization;
}
