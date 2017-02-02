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
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.io.Serializable;

/**
 * Created by Ramesh on 29-12-2016.
 */
@Data
@Entity
@Table(name="t_contact")
public class ContactDetails implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="fullName")
    private String fullName;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="role")
    private String role;

    @Column(name="officeName")
    private String officeName;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @Column(name="comments")
    private String comments;

}
