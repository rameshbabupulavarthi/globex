package com.globex.model.entity.partner;

import com.globex.model.entity.pm.Organization;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Ramesh on 29-12-2016.
 */
@Data
@Entity
@Table(name="banking")
public class BankingDetails implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="details")
    private String details;

    @Column(name="address")
    private String address;

    @Column(name="name")
    private String name;

    @Column(name="iban")
    private String iBan;

    @Column(name="swift_code")
    private String swiftCode;

    @Column(name="email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID")
    private Organization organization;
/*    @OneToOne
    @JoinColumn(name="contact")
    private ContactDetails contact;*/

}
