package com.globex.model.entity.partner;

import com.globex.model.entity.pm.Organization;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sunil Golla on 5/31/2017.
 */
@Data
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name="misc_rating")
public class MiscRating implements Serializable{

    @Id
    @Column(name="MISC_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long miscId;

    @Column(name="COMPANY_NAME")
    private String companyName;

    @Column(name="COMPANY_COUNTRY")
    private String companyCountry;

    @Column(name="COMPANY_WEBSITE")
    private String companyWebsite;

    @Column(name="COMPANY_RATING")
    private String companyRating;

    @Column(name="COMPANY_OUTLOOK")
    private String companyOutlook;

    @Column(name="COMPANY_ATTACHMENT")
    private String companyAttachment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID")
    private Organization organization;
}
