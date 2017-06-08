package com.globex.constants;

/**
 * Created by Sunil Golla on 1/11/2017.
 */
public class AppConstants {

    public static final String CRYPTO_EXCEPTION = "CRYPTO_EXCEPTION";
    public static final int DEFAULT_PAGE_SIZE=10;

    public enum OrgUserType{
        LM(1),PM(2);

        private Integer userType;
        OrgUserType(int userType) {
            this.userType=userType;
        }

        public Integer getUserType() {
            return userType;
        }
    }
}
