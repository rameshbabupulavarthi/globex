package com.globex.model.entity.pm;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Sunil Golla on 5/17/2017.
 */
@Data
@EqualsAndHashCode(exclude = {"organization"})
@Entity
@Table(name = "branch_office")
public class BranchOffice implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "BRANCH_OFFICE_ID", unique = true, nullable = false)
    private Integer branchOfficeId;

    @Column(name = "BRANCH_COUNTRY")
    private String branchCountry;

    @Column(name = "BRANCH_ADDRESS")
    private String branchAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID")
    private Organization organization;
}
