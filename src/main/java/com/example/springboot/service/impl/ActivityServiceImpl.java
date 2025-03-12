package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.dto.ActivityDTO;
import com.example.springboot.entity.Activity;
import com.example.springboot.mapper.ActivityMapper;
import com.example.springboot.test.mapper.testMapper;
import com.example.springboot.service.IActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author YAO
 * @create 2023-02-07 14:23
 */
@Service
@Slf4j
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    @Qualifier("test2")
    private testMapper testMapper;

    @Override
    public void submitAct(ActivityDTO activityDTO) {
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityDTO,activity);
        activity.setStatus(0);
        activityMapper.insert(activity);
    }

    @Override
    public Page<Activity> findAll(Page<Object> objectPage, String activityName) {
        testMapper.test();
        return activityMapper.findAll(objectPage,activityName);
    }

    @Override
    public void reject(Integer id) {
        activityMapper.reject(id);
    }

    @Override
    public void pass(Integer id) {
        activityMapper.pass(id);
    }

    @Override
    public Page<ActivityDTO> findMyAct(Page<Object> objectPage, String activityName, Integer uid) {
        Integer cid = getCid(uid);
        return activityMapper.findMyActivity(objectPage,activityName,cid);
    }

    @Override
    public Integer getCid(Integer uid) {
        if(activityMapper.getCidForMaster(uid) == null){
            return activityMapper.getCidForMember(uid);
        }
        return activityMapper.getCidForMaster(uid);
    }

    @Override
    public Activity findOneById(Integer aid) {
        return activityMapper.findOneById(aid);
    }

    @Override
    public void moneySubmit(ActivityDTO activityDTO) {
        activityMapper.moneySubmit(activityDTO);
    }

    @Override
    public Page<ActivityDTO> manageMoney(Page<Object> objectPage, String activityName) {
        return activityMapper.moneyManage(objectPage,activityName);
    }

    @Override
    public void passMoney(Integer aid) {
        activityMapper.passMoney(aid);
    }

    @Override
    public void rejectMoney(Integer aid) {
        activityMapper.rejectMoney(aid);
    }
}
