package com.globex.model.entity.pm;

import com.globex.model.entity.common.Attachment;
import com.globex.model.entity.lm.UWDepDetails;
import com.globex.model.entity.partner.*;
import com.globex.model.entity.user.User;
import com.globex.model.vo.lm.UWDepDetailsDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * Created by Sunil Golla on 2/10/2017.
 */
@Data
@EqualsAndHashCode(of = {"id"})
/*@ToString(exclude={"users","accountInfos","coverageAreas","attachments","registeredCountries","coverageContacts","branchOffices"})*/
@ToString(of = {"id","orgName"})
@Entity
@Table(name="organization")
public class Organization implements Serializable{

    @Id
    @Column(name="ORGANIZATION_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

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

    @Column(name="TELEPHONE")
    private String telePhone;

    @Column(name="ORGANIZATION_TYPE")
    private Integer orgUserType;

    @Column(name="APPROVED")
    private Integer approved;

    @Column(name="COMMENT")
    private String comment;

    @Column(name="LICENSED_STATE")
    private String licenceState;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="created_by")
    private User createdBy;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization",cascade = { CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    private Set<User> users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization",cascade = { CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    private Set<AccountInfo> accountInfos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization",cascade = { CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    private Set<CoverageArea> coverageAreas;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization",cascade = { CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    private Set<Attachment> attachments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization",cascade = { CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    private Set<RegisteredCountry> registeredCountries;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization",cascade = { CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    private Set<CoverageContact> coverageContacts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization",cascade = { CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    private Set<BranchOffice> branchOffices;


    /******************************** LM **************************************************/

    @OneToOne(mappedBy = "organization",fetch = FetchType.EAGER,cascade = { CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    private OrganizationDetail organizationDetails;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization",cascade = { CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    private List<MiscRating> miscRatings;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization",cascade = { CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    private List<LOB> lobs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization",cascade = { CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    private List<OrgRateRequirement> rateRequirements;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization",cascade = { CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    private List<CommissionRequirement> commissionRequirements;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization",cascade = { CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    private List<OrganizationHistory> organizationHistories;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization",cascade = { CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    private List<UWDepDetails> uwDepDetails;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization",cascade = { CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    private List<BankingDetails> bankingDetails;

}
