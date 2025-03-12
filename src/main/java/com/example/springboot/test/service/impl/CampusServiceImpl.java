package com.example.springboot.test.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.common.Constants;
import com.example.springboot.dto.CampusDTO;
import com.example.springboot.entity.Campus;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.CampusMapper;
import com.example.springboot.mapper.MemberMapper;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.ICampusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YAO
 * @create 2023-01-13 10:19
 */
@Service(value = "testCam")
@Slf4j
public class CampusServiceImpl extends ServiceImpl<CampusMapper, Campus> implements ICampusService {

    @Autowired
    private CampusMapper campusMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Page<Campus> findPage(Page<Campus> objectPage, String campusName) {
        return campusMapper.findPage(objectPage, campusName);
    }





    @Override
    public int createCampus(CampusDTO campusDTO) {
        QueryWrapper<Campus> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("campus_name", campusDTO.getCampusName());
        Campus campus = getOne(queryWrapper);
        if (campus != null) {
            throw new ServiceException(Constants.CODE_500, "已存在该社团");
        } else {
            return campusMapper.createNew(campusDTO);
        }
    }

    /**
     * 面向社长-我的社团
     * @param uid
     * @return
     */
    @Override
    public Campus findMyCampus(Integer uid) {
        QueryWrapper<Campus> queryWrapper = new QueryWrapper<>();
        User user = userMapper.findUserById(uid);
        if(user != null){
            if(user.getRole().equals("ROLE_MASTER")){
                queryWrapper.eq("master_id", uid);
                queryWrapper.eq("status", 1);
                Campus one = getOne(queryWrapper);
                return one;
            }else{
                return memberMapper.myCampus(uid);
            }
        }
        return null;
    }

    @Override
    public void passAppli(Integer cid, Integer mid) {
//        修改角色为master
        userMapper.changeRole(mid, "ROLE_MASTER");
//        通过成立社团申请
        campusMapper.passApplication(cid);
//        删除其他申请，因为一个人只能成立一个社团
        campusMapper.removeZeroCampus(mid);
//        同时如果此人申请了加入其他社团，也要删除相关信息
        if(campusMapper.checkJoin(mid) > 0){
            campusMapper.delMyJoin(mid);
        }
//        向社长-社团表添加数据
        campusMapper.addMCrelation(mid, cid);
    }

    @Override
    public int rejectAppli(Integer id) {
        return campusMapper.rejectApplication(id);
    }

    @Override
    public int checkJoin(Integer cid, Integer uid) {
        return campusMapper.checkJoinCampus(cid, uid);
    }

    @Override
    public int insertJoin(Integer cid, Integer uid) {
        return campusMapper.insertJoin(cid, uid);
    }

    @Override
    public List<Campus> myCamJoin(Integer userid) {
        return campusMapper.myJoinForCampus(userid);
    }

    @Override
    public int cancelJoinCam(Integer campusId, Integer userId) {
        return campusMapper.cancelJC(campusId, userId);
    }

    @Override
    public List<Campus> findMycfc(Integer userid) {
        return campusMapper.findMyCFC(userid);
    }

    @Override
    public int cancelCreateCam(Integer campusId, Integer userId) {
        return campusMapper.cancelCC(campusId, userId);
    }

    @Override
    public void updateMyCampus(CampusDTO campusDTO) {
        campusMapper.updateCampus(campusDTO);
    }

    @Override
    public Page<User> findStuForJoin(Page<User> object, Integer campusId) {
        return campusMapper.findStuForJoin(object, campusId);
    }

    @Override
    public int rejectJFC(Integer campusId, Integer userId) {
        return campusMapper.cancelJC(campusId, userId);
    }

    @Override
    public void passJoinC(Integer cid, Integer uid) {
//        删除申请表相关信息
        campusMapper.cancelJC(cid, uid);
        campusMapper.addMember(cid, uid);
        userMapper.changeRole(uid, "ROLE_CAMPUS");
//     删除申请人成立社团的申请
        if (campusMapper.findMyZeroCampus(uid).size() > 0) {
            campusMapper.removeZeroCampus(uid);
        }
    }

    @Override
    public CampusDTO getOne(Integer cid) {
        Campus one = campusMapper.getOne(cid);
        CampusDTO campusDTO = new CampusDTO();
        BeanUtil.copyProperties(one,campusDTO,true);
        campusDTO.setCampusId(one.getId());
        return campusDTO;
    }
}
