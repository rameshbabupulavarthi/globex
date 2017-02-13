package com.globex.model.entity.pm;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Sunil Golla on 2/10/2017.
 */
@Data
@ToString(exclude={"application","organization"})
@Entity
@Table(name="assigned_local_market")
public class AssignedLocalMarket {

    @Id
    @Column(name="ASSIGNED_LOCAL_MARKET_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name="APPLICATION_ID")
    private Application application;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name="LOCAL_MARKET_ID")
    private Organization organization;

    @Column(name="COUNTRY")
    private Integer country;
}
