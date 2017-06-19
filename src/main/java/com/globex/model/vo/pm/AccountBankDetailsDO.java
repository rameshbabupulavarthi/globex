package com.globex.model.vo.pm;

import com.globex.model.entity.pm.AccountBankDetails;
import lombok.Data;

/**
 * Created by Sunil Golla on 6/18/2017.
 */
@Data
public class AccountBankDetailsDO {

    private Long accountBankId;

    private String bankName;

    private String bankAddress;

    private String accountName;

    private String accountNo;

    private String iban;

    private String swiftCode;

    public AccountBankDetailsDO(){

    }

    public AccountBankDetailsDO(AccountBankDetails accountBankDetails){
        accountBankId=accountBankDetails.getAccountBankId();
        bankName=accountBankDetails.getBankName();
        bankAddress=accountBankDetails.getBankAddress();
        accountName=accountBankDetails.getAccountName();
        accountNo=accountBankDetails.getAccountNo();
        iban=accountBankDetails.getIban();
        swiftCode=accountBankDetails.getSwiftCode();
    }

    public AccountBankDetails value(){
        AccountBankDetails accountBankDetails=new AccountBankDetails();
        accountBankDetails.setAccountBankId(accountBankId);
        accountBankDetails.setBankName(bankName);
        accountBankDetails.setBankAddress(bankAddress);
        accountBankDetails.setAccountName(accountName);
        accountBankDetails.setAccountNo(accountNo);
        accountBankDetails.setIban(iban);
        accountBankDetails.setSwiftCode(swiftCode);
        return accountBankDetails;
    }
}
