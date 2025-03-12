package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.CampusMapper;
import com.example.springboot.mapper.MasterMapper;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YAO
 * @create 2023-02-03 9:44
 */
@Service
public class MasterServiceImpl implements MasterService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MasterMapper masterMapper;

    @Autowired
    private CampusMapper campusMapper;

    @Override
    public Page<User> findAllM(Page<Object> objectPage, Integer campusId, String username) {
        return campusMapper.findAllMember(objectPage, campusId, username);
    }

    @Override
    public void removeOneM(Integer campusId, Integer memberId) {
        userMapper.changeRole(memberId, "ROLE_SIMPLE");
        masterMapper.deleteOneMember(campusId, memberId);
    }

    @Override
    public Page<User> findStuForExit(Page<Object> objectPage, Integer cid) {
        return masterMapper.findAllExits(objectPage,cid);
    }

    @Override
    public void passExitAppli(Integer campusId, Integer memberId) {
        removeOneM(campusId,memberId);
        masterMapper.deleteExit(campusId,memberId);
    }

    @Override
    public void rejectExitAppli(Integer campusId, Integer memberId) {
        masterMapper.deleteExit(campusId,memberId);
    }

    @Override
    public void disbandCampus(Integer cid, Integer mid) {
        masterMapper.disband(cid,mid);
    }

    @Override
    public boolean checkDisband(Integer cid, Integer mid) {
        if(masterMapper.checkDis(cid,mid) > 0){
            return true;
        }
        return false;
    }
}
