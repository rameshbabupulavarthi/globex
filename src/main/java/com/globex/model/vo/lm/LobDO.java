package com.globex.model.vo.lm;

import com.globex.model.entity.partner.LOB;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Sunil Golla on 6/5/2017.
 */
@Data
public class LobDO implements Serializable {

    private Long orgLobId;

    private String lob;

    private Integer authorizedToWrite;

    private Integer willingnessToWrite;

    public LobDO (){

    }

    public LobDO(LOB lob1){
        orgLobId=lob1.getOrgLobId();
        lob=lob1.getLob();
        authorizedToWrite=lob1.getAuthorizedToWrite();
        willingnessToWrite=lob1.getWillingnessToWrite();
    }

    public LOB value(){
        LOB lob=new LOB();
        lob.setOrgLobId(orgLobId);
        lob.setLob(this.lob);
        lob.setAuthorizedToWrite(authorizedToWrite);
        lob.setWillingnessToWrite(willingnessToWrite);
        return lob;
    }
}
