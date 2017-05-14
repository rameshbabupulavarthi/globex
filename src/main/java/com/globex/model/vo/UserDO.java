package com.globex.model.vo;

import com.globex.model.entity.pm.Organization;
import com.globex.model.entity.user.User;
import com.globex.model.entity.user.UserRole;
import lombok.Data;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.Serializable;
import java.util.Date;

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
    private Integer phoneCountryCode;
    private Integer phoneAreaCode;
    private Long phone;
    private Integer phoneExtension;
    private Integer faxCountryCode;
    private Integer faxAreaCode;
    private Long fax;
    private Integer mobileCountryCode;
    private Long mobile;
    private Integer userType;
    private String comments;
    private Integer status;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zip;
    private String branchOffice;
    private String thumbnail;
    private CommonsMultipartFile thumbnailFile;
    private String externalId;
    private String role;
    private OrganizationDO organization;

    public UserDO(){

    }

    public UserDO(User user){
        this.id=user.getId();
        this.userName=user.getUserName();
        this.firstName=user.getFirstName();
        this.lastName=user.getLastName();
        this.email=user.getEmail();
        this.phoneCountryCode=user.getPhoneCountryCode();
        this.phoneAreaCode=user.getPhoneAreaCode();
        this.phone=user.getPhone();
        this.phoneExtension=user.getPhoneExtension();
        this.faxCountryCode=user.getFaxCountryCode();
        this.faxAreaCode=user.getFaxAreaCode();
        this.fax=user.getFax();
        this.mobileCountryCode=user.getMobileCountryCode();
        this.mobile=user.getMobile();
        this.userType=user.getUserType();
        this.comments=user.getComments();
        this.thumbnail=user.getThumbnail();
        this.status=user.getStatus();
        this.address=user.getAddress();
        this.city=user.getCity();
        this.state=user.getState();
        this.country=user.getCountry();
        this.zip=user.getZip();
        this.branchOffice=user.getBranchOffice();
        /*for(UserRole userRole:user.getUserRole()){
            this.role=userRole.getType();
            break;
        }*/
        this.organization=new OrganizationDO(user.getOrganization());
    }

    public User value(){

        User user=new User();
        user.setId(this.getId());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setUserName(this.getUserName());
        user.setEmail(this.getEmail());
        user.setPhoneCountryCode(this.getPhoneCountryCode());
        user.setPhoneAreaCode(this.getPhoneAreaCode());
        user.setPhone(this.getPhone());
        user.setPhoneExtension(this.getPhoneExtension());
        user.setFaxCountryCode(this.getFaxCountryCode());
        user.setFaxAreaCode(this.getFaxAreaCode());
        user.setFax(this.getFax());
        user.setMobileCountryCode(this.getMobileCountryCode());
        user.setMobile(this.getMobile());
        user.setUserType(this.getUserType());
        user.setComments(this.getComments());
        user.setStatus(this.getStatus());
        user.setAddress(this.getAddress());
        user.setCity(this.getCity());
        user.setState(this.getState());
        user.setCountry(this.getCountry());
        user.setZip(this.getZip());
        user.setBranchOffice(this.getBranchOffice());
        user.setStatus(UserDO.Status.ACTIVE.getValue());

        user.setThumbnail(this.getThumbnail());

        user.setPassword(this.getPassword());
        /*user.setCreatedDate(new Date());
        user.setModifiedDate(new Date());
        user.setPassword(encoder.encode(this.getPassword()));
        User currentUser=getCurrentUser();
        Organization organization=currentUser.getOrganization();*/
        return user;
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
