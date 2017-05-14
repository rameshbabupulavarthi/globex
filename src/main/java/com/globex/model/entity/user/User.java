package com.globex.model.entity.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.globex.model.entity.pm.Organization;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


/**
 * Created by Ramesh on 17-12-2016.
 */


@Data
@EqualsAndHashCode(exclude = {"organization"})
@ToString(exclude={"userRole"})
@Entity
@Table(name="user")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="USER_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORGANIZATION_ID", nullable = false)
    private Organization organization;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="USER_NAME")
    private String userName;

    @Column(name="EMAIL")
    private String email;

    @Column(name = "PHONE_COUNTRY_CODE")
    private Integer phoneCountryCode;

    @Column(name = "PHONE_AREA_CODE")
    private Integer phoneAreaCode;

    @Column(name = "PHONE")
    private Long phone;

    @Column(name = "PHONE_EXTENSION")
    private Integer phoneExtension;

    @Column(name = "FAX_COUNTRY_CODE")
    private Integer faxCountryCode;

    @Column(name = "FAX_AREA_CODE")
    private Integer faxAreaCode;

    @Column(name = "FAX")
    private Long fax;

    @Column(name = "MOBILE_COUNTRY_CODE")
    private Integer mobileCountryCode;

    @Column(name = "MOBILE")
    private Long mobile;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "USER_TYPE")
    private Integer userType;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "USER_STATUS")
    private Integer status;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "ZIP")
    private String zip;

    @Column(name = "BRANCH_OFFICE")
    private String branchOffice;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

    /*@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    private Set<UserRole> userRole;*/
}
