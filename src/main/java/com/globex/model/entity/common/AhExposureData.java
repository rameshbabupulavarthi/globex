package com.globex.model.entity.common;

import com.globex.model.entity.common.Application;
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
@Table(name = "ah_exposure_data")
public class AhExposureData implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "AH_EXPOSURE_DATA_ID", unique = true, nullable = false)
	private Integer ahExposureDataId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPLICATION_ID", nullable = false)
	private Application application;

    @Column(name = "COUNTRY", nullable = false, length = 50)
	private String country;

    @Column(name = "STATES", length = 150)
	private String states;

    @Column(name = "NUMBER_OF_EMP", length = 50)
	private String numberOfEmp;

    @Column(name = "TARGET_PREMIUM", precision = 17)
	private Double targetPremium;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ahExposureData")
	private Set<EligPerson> eligPersons;
}
