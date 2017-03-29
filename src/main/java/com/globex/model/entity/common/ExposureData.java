package com.globex.model.entity.common;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "exposure_data")
public class ExposureData implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "EXPOSURE_DATA_ID", unique = true, nullable = false)
    private Integer exposureDataId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPLICATION_ID", nullable = false)
	private Application application;

    @Column(name = "COUNTRY", nullable = false, length = 50)
	private String country;

    @Column(name = "EXPOSURE", precision = 11)
	private Double exposure;

    @Column(name = "RATE", precision = 17, scale = 8)
	private Double rate;

    @Column(name = "STATES", length = 150)
	private String states;
}
