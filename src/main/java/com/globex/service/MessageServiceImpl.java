package com.globex.service;

import com.globex.model.entity.Message;
import com.globex.repository.rdbms.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sunil Golla on 1/29/2017.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    public List<Message> list(Integer pageNumber,Integer pageSize){
        Pageable limit = (Pageable) new PageRequest(pageNumber, pageSize);
        Page<Message> messageList =messageRepository.findAll(limit);
        return messageList.getContent();
    }
}
