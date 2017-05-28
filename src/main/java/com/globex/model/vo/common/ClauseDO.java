package com.globex.model.vo.common;

import com.globex.model.entity.common.Clause;
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

    public ClauseDO(){

    }

    public ClauseDO (Clause clause){
        this.clauseId=clause.getClauseId();
        this.clauseName=clause.getName();
        this.clauseLob=clause.getLob();
        this.clauseComments=clause.getComments();
    }

    public Clause value(){
        Clause clause=new Clause();
        clause.setClauseId(clauseId);
        clause.setName(clauseName);
        clause.setLob(clauseLob);
        clause.setComments(clauseComments);
        return clause;
    }
}
