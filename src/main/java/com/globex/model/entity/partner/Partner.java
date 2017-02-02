package com.globex.model.entity.partner;

import com.globex.model.entity.user.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Ramesh on 30-12-2016.
 */
@Data
@Entity
@Table(name="t_partner")
public class Partner implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;


    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne
    @JoinColumn(name="org_id")
    private OrganizationDetails organizationDetails;

    @OneToOne
    @JoinColumn(name="bank_id")
    private BankingDetails bankingDetails;

    @OneToOne
    @JoinColumn(name="account_id")
    private AccountDetails accountDetails;

    @OneToOne
    @JoinColumn(name="contact_id")
    private ContactDetails contactDetails;

    @OneToOne
    @JoinColumn(name="finance_id")
    private FinancialDetails financialDetails;

    @OneToOne
    @JoinColumn(name="network_id")
    private NetworkDetails networkDetails;
    
    @Column(name="created_date")
    private String createdDate;

    @Column(name="created_by")
    private String createdBy;

    @Column(name="modified_date")
    private String modifiedDate;

    @Column(name="modified_by")
    private String modifiedBy;

}
