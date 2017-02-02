package com.globex.service;

import com.globex.model.entity.Message;

import java.util.List;

/**
 * Created by Sunil Golla on 1/29/2017.
 */
public interface MessageService {

    public List<Message> list(Integer pageNo,Integer pageSize);
}
