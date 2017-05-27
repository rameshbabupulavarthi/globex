package com.globex.model.entity.common;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Sunil Golla on 5/23/2017.
 */
@Data
@Entity
@Table(name = "tax")
public class Tax {

    @Id
    @Column(name = "TAX_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long taxId;

    @Column(name = "TAX_TYPE")
    private String taxType;

    @Column(name = "LOB")
    private String lob;

    @Column(name = "PERCENT")
    private String percent;

    @Column(name = "APPLIED_TO")
    private String appliedTo;

    @Column(name = "AMOUNT")
    private String amount;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "RESPONSIBILITY")
    private String resposiblility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;

}
