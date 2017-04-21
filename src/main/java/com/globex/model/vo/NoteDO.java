package com.globex.model.vo;

import com.globex.model.entity.common.Note;
import com.globex.model.entity.user.User;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sunil Golla on 3/29/2017.
 */
@Data
public class NoteDO implements Serializable {

    private Integer noteId;

/*    private Organization organization;*/

    private UserDO user;

    private String information;

    private String notes;

    private Date dateCreated;

    private Long fileId;

    public NoteDO(){

    }

    public NoteDO(Note note){
        this.noteId=note.getNoteId();
        this.information= note.getInformation();
        this.notes=note.getNotes();
        this.user=note.getUser()!=null?new UserDO(note.getUser()):null;
        this.dateCreated=note.getDateCreated();
    }
}
