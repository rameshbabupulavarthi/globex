package com.globex.constants;

/**
 * Created by Sunil Golla on 1/14/2017.
 */
public enum Role{
    ROLE_SUPER_ADMIN("ROLE_SUPER_ADMIN",1),ROLE_ADMIN("ROLE_ADMIN",2),
    ROLE_GLOBEX_ADMIN("ROLE_GLOBEX_ADMIN",3),ROLE_GLOBEX("ROLE_GLOBEX_USER",4),
    ROLE_PM_ADMIN("ROLE_PM_ADMIN",5),ROLE_PM_USER("ROLE_PM_USER",6),
    ROLE_LM_ADMIN("ROLE_LM_ADMIN",7),ROLE_LM_USER("ROLE_LM_USER",8),
    ROLE_SUPPORT("ROLE_SUPPORT",40),
    ROLE_USER("ROLE_USER",50);
    private String roleName;
    private Integer roleValue;
    Role(String roleName,int roleValue) {
        this.roleName=roleName;
        this.roleValue=roleValue;
    }

    public static Role getValue(int roleValue){
        for(Role role: Role.values()){
            if(role.getRoleValue()==roleValue){
                return role;
            }
        }
        return null;
    }

    public static Role getValue(String roleName){
        for(Role role: Role.values()){
            if(role.getRoleName().equals(roleName)){
                return role;
            }
        }
        return null;
    }

    public String getRoleName() {
        return roleName;
    }

    public Integer getRoleValue() {
        return roleValue;
    }
}
