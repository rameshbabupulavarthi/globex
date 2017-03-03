package com.globex.service;

import com.globex.model.entity.common.Communication;
import org.springframework.data.domain.Page;

/**
 * Created by Sunil Golla on 2/20/2017.
 */
public interface CommunicationService {

    public Page<Communication> list(Integer pageNumber,Integer pageSize);
}
