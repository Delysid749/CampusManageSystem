package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.entity.Campus;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.CampusMapper;
import com.example.springboot.mapper.SchoolMapper;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YAO
 * @create 2023-02-03 9:44
 */
@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CampusMapper campusMapper;

    @Override
    public Page<Campus> findPageFA(Page<Campus> objectPage, String campusName) {
        return schoolMapper.findPageFA(objectPage, campusName);
    }

    @Override
    public Page<Campus> findPageFUA(Page<Campus> objectPage, String campusName) {
        return schoolMapper.findPageFUA(objectPage, campusName);
    }

    @Override
    public Page<Campus> findDisband(Page<Campus> objectPage, String campusName) {
        return schoolMapper.findAllDisbind(objectPage,campusName);
    }

    @Override
    public void rejectDisband(Integer cid, Integer mid) {
        schoolMapper.deleteDisband(cid,mid);
    }

    @Override
    public void passDisband(Integer cid, Integer mid) {
        schoolMapper.deleteDisband(cid,mid);
//        将社长和成员的角色改为simple
        userMapper.changeRole(mid,"ROLE_SIMPLE");
        List<User> disbandMember = schoolMapper.findDisbandMember(cid);
        for (User user : disbandMember) {
            userMapper.changeRole(user.getId(),"ROLE_SIMPLE");
        }
//        删除社团信息
        campusMapper.cancelCC(cid,mid);
        schoolMapper.deleteCampusExit(cid);
        schoolMapper.deleteCampusJoin(cid);
//        删除member信息
        schoolMapper.deleteMember(cid);
//        删除社长表
        campusMapper.delMasCam(mid);
    }


}
