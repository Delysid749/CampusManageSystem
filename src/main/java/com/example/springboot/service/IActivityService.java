package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.common.Result;
import com.example.springboot.dto.ActivityDTO;
import com.example.springboot.entity.Activity;

/**
 * @author YAO
 * @create 2023-02-07 14:20
 */
public interface IActivityService extends IService<Activity> {

    void submitAct(ActivityDTO activityDTO);

    Page<Activity> findAll(Page<Object> objectPage, String activityName);

    void reject(Integer id);

    void pass(Integer id);

    Page<ActivityDTO> findMyAct(Page<Object> objectPage, String activityName, Integer uid);

    Integer getCid(Integer uid);

    Activity findOneById(Integer aid);

    void moneySubmit(ActivityDTO activityDTO);

    Page<ActivityDTO> manageMoney(Page<Object> objectPage, String activityName);

    void passMoney(Integer aid);

    void rejectMoney(Integer aid);
}
