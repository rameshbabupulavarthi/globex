package com.globex.model.entity.common;

import com.globex.model.entity.common.File;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "mus_account_fact_sheet")
public class MusAccountFactSheet implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "FACT_SHEET_ID", unique = true, nullable = false)
	private Integer factSheetId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILE_ID", nullable = false)
	private File file;

    @Column(name = "GLOBAL_RATE", precision = 11)
	private Double globalRate;

    @Column(name = "PROD_BROKER")
	private String prodBroker;

    @Column(name = "TOTAL_SALES_SHIPMENTS", precision = 11)
    private Double totalSalesShipments;

    @Column(name = "SHIPMENTS_BY_COUNTRY", precision = 11)
    private Double shipmentsByCountry;

    @Column(name = "NO_OF_COUNTRIES")
    private Integer noOfCountries;

    @Column(name = "EST_GLOBAL_FEE", precision = 11)
    private Double estGlobalFee;

    @Column(name = "STATES_LOCAL_COLLECTION", length = 50)
    private String statesLocalCollection;

    @Column(name = "LOCAL_BROKER_ROLE", length = 30)
	private String localBrokerRole;

    @Column(name = "REINSURED_PERCENTAGE", precision = 7, scale = 4)
	private Double reinsuredPercentage;

    @Column(name = "CLAIMS_HANDLE", length = 30)
	private String claimsHandle;

    @Column(name = "LOCAL_SERVICES", length = 50)
	private String localServices;

    @Column(name = "LIMITS_DEUCTIBLE_PERILS", length = 50)
	private String limitsDeuctiblePerils;

    @Column(name = "ESTIMATED_VALUES")
    private String estimatedValues;

    @Column(name = "ALLOCATED_PREMIUM", precision = 11)
    private Double allocatedPremium;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "musAccountFactSheet")
    private Set<MusAccountFactSheetAction> musAccountFactSheetActions;
}
