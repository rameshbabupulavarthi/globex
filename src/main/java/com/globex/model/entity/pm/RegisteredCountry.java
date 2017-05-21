package com.globex.model.entity.pm;

/**
 * Created by Sunil Golla on 5/17/2017.
 */
import com.globex.model.entity.common.Attachment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@EqualsAndHashCode(exclude = {"organization"})
@Entity
@Table(name = "registered_country")
public class RegisteredCountry implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "REGISTERED_COUNTRY_ID")
    private Long registeredCountryId;

    @Column(name = "COUNTRY_NAME")
    private String countryName;

    @Column(name = "REGISTRATION_NUMBER")
    private String registrationNumber;

    @Column(name = "REGISTRATION_DATE")
    private Timestamp registrationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization",cascade = { CascadeType.ALL,CascadeType.PERSIST})
    @Fetch(FetchMode.SELECT)
    private Set<Attachment> regAttachments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID")
    private Organization organization;
}
