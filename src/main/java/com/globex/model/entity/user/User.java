package com.globex.model.entity.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;



/**
 * Created by Ramesh on 17-12-2016.
 */


@Data
@ToString(exclude={"userRole"})
@Entity
@Table(name="t_user")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name="USERNAME")
    private String username;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="FIRSTNAME")
    private String firstname;

    @Column(name="LASTNAME")
    private String lastname;

    @Column(name="EMAIL")
    private String email;

    @Column(name="TELEPHONE")
    private String telephone;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name="external_id")
    private String externalId;

    @Column(name="status")
    private Integer status;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

    @OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<UserRole> userRole;


    public void addUserRole(UserRole userRole){
        if(getUserRole() == null){
            setUserRole(new HashSet<UserRole>());
        }
        getUserRole().add(userRole);
    }

    public List<String> getCurrentUserRoleList(){
        List<String> userRoles = new ArrayList<String>();
        if(getUserRole()!= null && !getUserRole().isEmpty()){
            for(UserRole userRole:getUserRole()){
                userRoles.add(userRole.getType());
            }
            return userRoles;
        }
        return null;
    }


}
