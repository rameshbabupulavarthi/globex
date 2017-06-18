package com.globex.model.vo.common;

import com.globex.model.entity.common.Clause;
import com.utils.StringUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Sunil Golla on 5/27/2017.
 */
@Data
public class ClauseDO implements Serializable{

    private Long clauseId;

    private String clauseName;

    private String clauseLob;

    private String clauseComments;

    private String clauseAttach;

    public ClauseDO(){

    }

    public ClauseDO (Clause clause){
        this.clauseId=clause.getClauseId();
        this.clauseName=clause.getName();
        this.clauseLob=clause.getLob();
        this.clauseComments=clause.getComments();
        this.clauseAttach=clause.getAttachment();
    }

    public Clause value(){
        Clause clause=new Clause();
        clause.setClauseId(clauseId);
        clause.setName(StringUtils.getValue(clauseName));
        clause.setLob(StringUtils.getValue(clauseLob));
        clause.setComments(StringUtils.getValue(clauseComments));
        clause.setAttachment(clauseAttach);
        return clause;
    }
}
