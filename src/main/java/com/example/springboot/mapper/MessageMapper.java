package com.example.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.dto.CampusDTO;
import com.example.springboot.entity.Message;
import org.apache.ibatis.annotations.Insert;

/**
 * @author YAO
 * @create 2023-03-05 15:12
 */
public interface MessageMapper {

    @Insert("insert into message (campus_id,username,message) values (#{campusId},'游客',#{message})")
    void messageForVisitor(CampusDTO campusDTO);

    @Insert("insert into message (campus_id,username,message) values (#{campusId},#{userName},#{message})")
    void messageForOthers(CampusDTO campusDTO);

    Page<Message> showMessages(Page<Message> objectPage, Integer cid);
}
