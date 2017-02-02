package com.globex.model.entity.partner;

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
@Table(name="t_banking")
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

    @OneToOne
    @JoinColumn(name="contact")
    private ContactDetails contact;

}
