package com.globex.model.entity.common;

import com.globex.model.entity.common.Application;
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
@Table(name = "locations_values")
public class LocationsValues implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "LOCATION_VALUE_ID", unique = true, nullable = false)
	private Integer locationValueId;

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
