package com.globex.model.entity.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sunil Golla on 5/23/2017.
 */
@Data
@EqualsAndHashCode(exclude = {"country"})
@ToString(exclude = {"country"})
@Entity
@Table(name = "tax")
public class Tax implements Serializable {

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
    private String responsiblility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;

}
