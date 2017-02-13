package com.globex.model.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ramesh on 17-12-2016.
 */
@Data
@ToString(exclude={"user"})
@EqualsAndHashCode(of={"id"})
@Entity
@Table(name="t_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="default_role")
    private Boolean defaultRole;

    @Column(name="type")
    private String type;

    @Column(name="created_by")
    private Long createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "status")
    private Integer status;

    @Column(name="permission")
    private String permission;
}