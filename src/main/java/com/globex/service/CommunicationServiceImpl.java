package com.globex.service;

import com.globex.model.entity.common.Communication;
import com.globex.repository.rdbms.CommunicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sunil Golla on 2/20/2017.
 */
@Service
public class CommunicationServiceImpl implements CommunicationService {

    @Autowired
    CommunicationRepository communicationRepository;

    public Page<Communication> list(Integer pageNumber,Integer pageSize){
        Pageable limit = (Pageable) new PageRequest(pageNumber, pageSize);
        Page<Communication> communicationPage =communicationRepository.findAll(limit);
        return communicationPage;
    }
}
