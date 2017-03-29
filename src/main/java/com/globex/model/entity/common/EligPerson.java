package com.globex.model.entity.common;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "elig_person")
public class EligPerson implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ELIG_PERSON_ID", unique = true, nullable = false)
	private Integer eligPersonId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AH_EXPOSURE_DATA_ID", nullable = false)
	private AhExposureData ahExposureData;

    @Column(name = "DESCRIPTION", length = 250)
	private String description;

    @Column(name = "NUM_OF_PERSONS", length = 50)
	private String numOfPersons;

    @Column(name = "PRINCIPLE_SUM", length = 50)
	private String principleSum;

    @Column(name = "COVERAGE_DESIRED")
	private Integer coverageDesired;

    @Column(name = "OTHER_COVERAGE_DESIRED", length = 50)
	private String otherCoverageDesired;

    @Column(name = "TOTAL_PERSONS", length = 50)
	private String totalPersons;
}
