package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.dto.CampusDTO;
import com.example.springboot.entity.Message;
import com.example.springboot.mapper.MessageMapper;
import com.example.springboot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YAO
 * @create 2023-03-05 15:12
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public void save(CampusDTO campusDTO) {
        if(campusDTO.getUserName() == null){
            messageMapper.messageForVisitor(campusDTO);
        }else {
            messageMapper.messageForOthers(campusDTO);
        }
    }

    @Override
    public Page<Message> showMessages(Page<Message> objectPage, Integer cid) {
        return messageMapper.showMessages(objectPage,cid);
    }
}
