package com.globex.model.vo;

import com.globex.model.entity.pm.Organization;
import com.globex.model.entity.user.User;
import com.globex.model.entity.user.UserRole;
import lombok.Data;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.Serializable;

/**
 * Created by Sunil Golla on 1/12/2017.
 */
@Data
public class UserDO implements Serializable {

    private Long id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Long telephone;
    private String thumbnail;
    private CommonsMultipartFile thumbnailFile;
    private String externalId;
    private Integer status;
    private String role;
    private OrganizationDO organization;

    public UserDO(User user){
        this.id=user.getId();
        this.userName=user.getUserName();
        this.firstName=user.getFirstName();
        this.lastName=user.getLastName();
        this.email=user.getEmail();
        this.telephone=user.getPhone();
        this.thumbnail=user.getThumbnail();
        this.status=user.getStatus();
        for(UserRole userRole:user.getUserRole()){
            this.role=userRole.getType();
            break;
        }
        this.organization=new OrganizationDO(user.getOrganization());
    }

    public UserDO(){


    }

    public enum Status{
        ACTIVE(1),DISABLED(2),EXPIRED(3),DELETED(4);

        private Integer status;
        Status(Integer status){
            this.status=status;
        }
        public Integer getValue(){
            return status;
        }
    }
}
