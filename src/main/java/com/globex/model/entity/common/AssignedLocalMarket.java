package com.globex.model.entity.common;

import com.globex.model.entity.common.Application;
import com.globex.model.entity.pm.Organization;
import lombok.Data;
import lombok.ToString;

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
@ToString(exclude={"application","organization"})
@Entity
@Table(name = "assigned_local_market")
public class AssignedLocalMarket implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ASSIGNED_LOCAL_MARKET_ID", unique = true, nullable = false)
	private Integer assignedLocalMarketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPLICATION_ID", nullable = false)
	private Application application;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCAL_MARKET_ID")
	private Organization organization;

    @Column(name = "COUNTRY", nullable = false, length = 50)
	private String country;
}
