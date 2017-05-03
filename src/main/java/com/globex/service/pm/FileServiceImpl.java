package com.globex.service.pm;

import com.globex.model.entity.common.*;
import com.globex.model.entity.pm.Organization;
import com.globex.model.entity.user.User;
import com.globex.model.vo.ExposureDataDO;
import com.globex.model.vo.PageModel;
import com.globex.model.vo.pm.ApplicationDO;
import com.globex.model.vo.pm.FileInfoDO;
import com.globex.repository.rdbms.pm.FileRepository;
import com.globex.service.UserService;
import com.utils.DateUtil;
import com.utils.FileUtils;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.sql.Timestamp;
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
                if(entry.getKey().equals("orgId")){
                    criteria.createAlias("organization","organization");
                    criteria.add(Restrictions.eq("organization.id",entry.getValue()));
                    criteriaForCount.createAlias("organization","organization");
                    criteriaForCount.add(Restrictions.eq("organization.id",entry.getValue()));
                }else {
                    criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
                    criteriaForCount.add(Restrictions.eq(entry.getKey(), entry.getValue()));
                }
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
            fileInfo.getFileAttachments();
            FileInfoDO fileInfoDO=new FileInfoDO(fileInfo);
            fileInfoDOs.add(fileInfoDO);
        }
        pageModel.setContent(fileInfoDOs);
        pageModel.setTotalRecords(totalCount);
        return pageModel;
    }

    //TODO:needs modification
    public void save(ApplicationDO applicationDO,String rootPath){
        User user=userService.getCurrentUser();
        Organization organization=user.getOrganization();

        File file=new File();
        FileInfoDO fileInfo=applicationDO.getFileInfo();
        if(fileInfo!=null){
            file.setFileId(fileInfo.getFileId());
        }
        file.setOrganization(organization);
        file.setForwardTo(user);
        file.setUpdatedBy(user);
        file.setCreatedBy(user);
        file.setFileStatus(0);
        //file.setProspect(0);
        file.setDateCreated(new Timestamp(System.currentTimeMillis()));
        file.setDateUpdated(new Timestamp(System.currentTimeMillis()));

        Set<Application> applications=new HashSet<Application>();
        Application application=applicationDO.value();
        application.setFile(file);

        //TODO:TEMPORARY CODE
        application.setPolicyStartDate(DateUtil.getTimestamp(applicationDO.getPolicyStartDate()));
        application.setPolicyEndDate(DateUtil.getTimestamp(applicationDO.getPolicyEndDate()));
        application.setCollectionType(1);
        application.setApplicationStatus(1);
        application.setDateEmailedLocalMarkets(new Timestamp(System.currentTimeMillis()));
        application.setGlOccurence(Boolean.TRUE);
        application.setPlOccurence(Boolean.TRUE);
        application.setElOccurence(Boolean.TRUE);
        application.setGlClaimsMade(Boolean.TRUE);
        application.setPlClaimsMade(Boolean.TRUE);
        application.setElClaimsMade(Boolean.TRUE);

        applicationDO.getExposureDataValues(application);
        applicationDO.getlocalBrokerContactValues(application);
        applications.add(application);
        file.setApplications(applications);
        File filePersisted=fileRepository.save(file);

        Session session=sessionFactory.openSession();
        CommonsMultipartFile fileToUpload=applicationDO.getAttachment();
        if (fileToUpload != null && fileToUpload.getBytes().length > 0) {
            String relativePath = FileUtils.uploadFile(fileToUpload, rootPath);
            FileAttachment fileAttachment=new FileAttachment();
            fileAttachment.setFile(filePersisted);
            fileAttachment.setFileName(relativePath);
            session.save(fileAttachment);
        }
    }
}
