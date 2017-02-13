package com.globex.model.entity.pm;

import com.globex.model.entity.user.User;
import com.globex.model.entity.user.UserRole;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by Sunil Golla on 2/10/2017.
 */
@Data
@ToString(exclude={"application","organization","user"})
@Entity
@Table(name="file")
public class FileInfo implements Serializable {

    @Id
    @Column(name="FILE_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id ;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name="PARTNER_MARKET_ID")
    //private Organization partnerMarketId;
    private Organization organization;

    @Column(name="FORWARD_TO")
    private Integer forwardTo ;

    @Column(name="FILE_STATUS")
    private Integer fileStatus ;

    @Column(name="PROSPECT")
    private Integer prospect;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name="CREATED_BY")
    private User user;

    @Column(name="DATE_CREATED")
    private Timestamp dateCreated;

    @Column(name="UPDATED_BY")
    private Integer updatedBy;

    @Column(name="DATE_UPDATED")
    private Timestamp dateUpdated;

    @OneToOne(mappedBy="fileInfo", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Application application;

}
