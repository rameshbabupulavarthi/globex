package com.globex.model.entity.partner;

import com.globex.model.entity.pm.Organization;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Sunil Golla on 6/3/2017.
 */
@Data
@EqualsAndHashCode(of = {"orgLobId"})
@ToString(exclude={"organization"})
@Entity
@Table(name="org_lob")
public class LOB {

    @Id
    @Column(name="ORG_LOB_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long orgLobId;

    @Column(name="ORG_LOB")
    private String lob;

    @Column(name="AUT_TO_WRT")
    private Integer authorizedToWrite;

    @Column(name="WIL_TO_WRT")
    private Integer willingnessToWrite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID")
    private Organization organization;

}
