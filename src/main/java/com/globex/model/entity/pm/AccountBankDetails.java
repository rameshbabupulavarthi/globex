package com.globex.model.entity.pm;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Created by Sunil Golla on 6/18/2017.
 */
@Data
@EqualsAndHashCode(exclude = {"accountInfo"})
@Entity
@Table(name="account_banking")
public class AccountBankDetails {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long accountBankId;

    @Column(name="bankName")
    private String bankName;

    @Column(name="bankAddress")
    private String bankAddress;

    @Column(name="accountName")
    private String accountName;

    @Column(name="accountNo")
    private String accountNo;

    @Column(name="iBan")
    private String iban;

    @Column(name="swift_code")
    private String swiftCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACC_ID")
    private AccountInfo accountInfo;
}
