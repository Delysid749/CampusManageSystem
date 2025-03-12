package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.dto.CampusDTO;
import com.example.springboot.entity.Message;

/**
 * @author YAO
 * @create 2023-03-05 15:12
 */
public interface MessageService {
    void save(CampusDTO campusDTO);

    Page<Message> showMessages(Page<Message> objectPage, Integer cid);
}
