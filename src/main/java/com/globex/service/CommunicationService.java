package com.globex.service;

import com.globex.model.entity.common.Communication;
import com.globex.model.entity.common.Note;
import com.globex.model.vo.CommunicationDO;
import com.globex.model.vo.NoteDO;
import com.globex.model.vo.PageModel;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Sunil Golla on 2/20/2017.
 */
public interface CommunicationService {

    public PageModel<CommunicationDO> list(PageModel<CommunicationDO> pageModel);

    public List<Communication> listById(Long fileId);

    public String saveComments(Long fileId,String comments);

    public String saveComment(CommunicationDO communicationDO);

    public List<Note> listNotesByFile(Long fileId);

    public String saveNote(NoteDO noteDO);
}
