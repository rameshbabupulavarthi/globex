package com.globex.service;

import com.globex.model.entity.common.Communication;
import com.globex.model.entity.common.File;
import com.globex.model.entity.common.Note;
import com.globex.model.entity.pm.Organization;
import com.globex.model.entity.user.User;
import com.globex.model.vo.NoteDO;
import com.globex.model.vo.PageModel;
import com.globex.repository.rdbms.CommunicationRepository;
import com.globex.repository.rdbms.lm.OrganizationDetailsRepository;
import com.globex.repository.rdbms.pm.FileRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Sunil Golla on 2/20/2017.
 */
@Service
public class CommunicationServiceImpl implements CommunicationService {

    @Autowired
    CommunicationRepository communicationRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    UserService userService;

    @Autowired
    OrganizationDetailsRepository organization;


    public PageModel<Communication> list(PageModel pageModel){

        Session session=sessionFactory.openSession();
        Criteria criteria=session.createCriteria(Communication.class);
        Criteria criteriaForCount=session.createCriteria(Communication.class);

        if(pageModel.getFilters()!=null){
            Map<String,Object> filters=pageModel.getFilters();
            for(Map.Entry<String,Object> entry: filters.entrySet()){
                criteria.add(Restrictions.eq(entry.getKey(),entry.getValue()));
                criteriaForCount.add(Restrictions.eq(entry.getKey(),entry.getValue()));
            }
        }

        if(pageModel.getSortFields()!=null){
            Map<String,PageModel.SortOrder> sortFields=pageModel.getSortFields();
            for(Map.Entry<String,PageModel.SortOrder> entry:sortFields.entrySet()){
                if(PageModel.SortOrder.ASC.equals(entry.getValue())){
                    criteria.addOrder(Order.asc(entry.getKey()));
                }else{
                    criteria.addOrder(Order.desc(entry.getKey()));
                }
            }
        }
        criteria.setFirstResult(pageModel.getPageNo()*pageModel.getPageSize());
        criteria.setMaxResults(pageModel.getPageSize());
        criteriaForCount.setProjection(Projections.rowCount());
        Long totalCount = (Long) criteriaForCount.uniqueResult();
        List<Communication> list= criteria.list();
        pageModel.setContent(list);
        pageModel.setTotalRecords(totalCount);
        return pageModel;
    }

    public List<Communication> listById(Long fileId){
        Session session=sessionFactory.openSession();
        Criteria criteria=session.createCriteria(Communication.class);
        criteria.addOrder(Order.desc("id"));
        criteria.createAlias("file","file");
        criteria.add(Restrictions.eq("file.id",fileId));
        List<Communication> communications=criteria.list();
        return communications;
    }

    public String saveComments(Long fileId,String comments){
        File file=fileRepository.findOne(fileId);
        User user=userService.getCurrentUser();
        Communication communication=new Communication();
        communication.setContent(comments);
        communication.setFile(file);
        communication.setUser(user);
        communication.setOrganizationByFromOrganizationId(user.getOrganization());
        communication.setOrganizationByToOrganizationId(file.getOrganization());
        communication.setShowToGlobex(Boolean.TRUE);
        communication.setShowToLm(Boolean.TRUE);
        communication.setShowToPm(Boolean.TRUE);
        communication.setReplySent(Boolean.TRUE);
        communication.setDateCreated(new Date());

        Session session=sessionFactory.openSession();
        communicationRepository.save(communication);
        return "success";
    }

    public List<Note> listNotesByFile(Long fileId){
        Session session=sessionFactory.openSession();
        Criteria criteria=session.createCriteria(Note.class);
        criteria.addOrder(Order.desc("id"));
        criteria.createAlias("file","file");
        criteria.add(Restrictions.eq("file.id",fileId));
        List<Note> notes=criteria.list();
        return notes;
    }

    public String saveNote(NoteDO noteDO){
        File file=fileRepository.findOne(noteDO.getFileId());
        User user=userService.getCurrentUser();
        Note note=new Note();
        note.setInformation(noteDO.getInformation());
        note.setNotes(noteDO.getNotes());
        note.setUser(user);
        note.setDateCreated(new Date());
        note.setFile(file);
        note.setOrganization(user.getOrganization());

        Session session=sessionFactory.openSession();
        session.save(note);
        return "success";
    }
}
