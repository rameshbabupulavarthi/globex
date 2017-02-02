package com.globex.model.entity.partner;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Ramesh on 29-12-2016.
 */
@Data
@Entity
@Table(name="t_organization")
public class OrganizationDetails implements Serializable{

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="phone")
    private String phone;

    @Column(name="website")
    private String website;

    @Column(name="email")
    private String email;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="zipcode")
    private String zipcode;

    @Column(name="country")
    private String country;

    @OneToOne
    @JoinColumn(name="contact")
    private ContactDetails contact;

}