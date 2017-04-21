package com.globex.service.pm;

import com.globex.model.entity.common.Application;
import com.globex.model.entity.common.File;
import com.globex.model.entity.pm.Organization;
import com.globex.model.entity.user.User;
import com.globex.model.vo.PageModel;
import com.globex.model.vo.pm.ApplicationDO;
import com.globex.model.vo.pm.FileInfoDO;
import com.globex.repository.rdbms.pm.FileRepository;
import com.globex.service.UserService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Sunil Golla on 2/11/2017.
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileRepository fileRepository;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    UserService userService;

    public PageModel<FileInfoDO> list(PageModel<FileInfoDO> pageModel){

        Session session=sessionFactory.openSession();
        Criteria criteria=session.createCriteria(File.class);
        Criteria criteriaForCount=session.createCriteria(File.class);

        if(pageModel.getFilters()!=null){
            Map<String,Object> filters=pageModel.getFilters();
            for(Map.Entry<String,Object> entry: filters.entrySet()){
                criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
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
        }else{
            criteria.addOrder(Order.desc("fileId"));
        }
        criteria.setFirstResult(pageModel.getPageNo()*pageModel.getPageSize());
        criteria.setMaxResults(pageModel.getPageSize());

        criteria.setFirstResult(pageModel.getPageNo()*pageModel.getPageSize());
        criteria.setMaxResults(pageModel.getPageSize());
        criteriaForCount.setProjection(Projections.rowCount());
        Long totalCount = (Long) criteriaForCount.uniqueResult();
        List<File> list= criteria.list();
        List<FileInfoDO> fileInfoDOs=new ArrayList<FileInfoDO>();
        for(File fileInfo:list){
            FileInfoDO fileInfoDO=new FileInfoDO(fileInfo);
            fileInfoDOs.add(fileInfoDO);
        }
        pageModel.setContent(fileInfoDOs);
        pageModel.setTotalRecords(totalCount);
        return pageModel;
    }

    //TODO:needs modification
    public void save(ApplicationDO applicationDO){
        User user=userService.getCurrentUser();
        Organization organization=user.getOrganization();
        File file=new File();
        file.setOrganization(organization);
        file.setCreatedBy(user);
        file.setUpdatedBy(user);
        file.setForwardTo(user);
        file.setFileStatus(0);
        file.setProspect(0);

        Set<Application> applications=new HashSet<Application>();
        Application application=applicationDO.getValue();
        applications.add(application);
        file.setApplications(applications);
        fileRepository.save(file);
    }
}
