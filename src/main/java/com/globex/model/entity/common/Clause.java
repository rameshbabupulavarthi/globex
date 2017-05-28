package com.globex.model.entity.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Sunil Golla on 5/24/2017.
 */
@Data
@EqualsAndHashCode(exclude = {"country"})
@ToString(exclude = {"country"})
@Entity
@Table(name = "CLAUSE")
public class Clause implements Serializable {

    @Id
    @Column(name = "CLAUSE_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long clauseId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LOB")
    private String lob;

    @Column(name = "COMMENTS")
    private String comments;

    /*@OneToMany(fetch = FetchType.LAZY,mappedBy = "attachments")
    private Set<Attachment> attachments;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;

}
