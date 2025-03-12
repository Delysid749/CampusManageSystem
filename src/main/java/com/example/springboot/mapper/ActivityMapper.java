package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.dto.ActivityDTO;
import com.example.springboot.entity.Activity;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author YAO
 * @create 2023-02-07 14:09
 */

public interface ActivityMapper extends BaseMapper<Activity> {



    Page<Activity> findAll(Page<Object> objectPage, String activityName);

    @Update("update activity set status = 2 where id =#{id}")
    void reject(Integer id);

    @Update("update activity set status = 1 where id =#{id}")
    void pass(Integer id);

    Page<ActivityDTO> findMyActivity(Page<Object> objectPage, String activityName, Integer cid);

    @Select("select campus_id from master_campus where master_id =#{uid}")
    Integer getCidForMaster(Integer uid);

    @Select("select campus_id from member where member_id =#{uid}")
    Integer getCidForMember(Integer uid);

    Activity findOneById(Integer aid);

    void moneySubmit(ActivityDTO activityDTO);

    Page<ActivityDTO> moneyManage(Page<Object> objectPage, String activityName);

    @Update("update activity_money set money_status = 1 where activity_id =#{aid}")
    void passMoney(Integer aid);

    @Update("update activity_money set money_status = 2 where activity_id =#{aid}")
    void rejectMoney(Integer aid);
}
