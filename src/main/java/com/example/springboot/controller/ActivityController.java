package com.example.springboot.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.dto.ActivityDTO;
import com.example.springboot.entity.Activity;
import com.example.springboot.service.IActivityService;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author YAO
 * @create 2023-02-03 11:37
 */
@RestController
@RequestMapping("/act")
public class ActivityController {

    @Autowired
    private IActivityService activityService;




    @PostMapping("/submit")
    public Result submit(@RequestBody ActivityDTO activityDTO){
        activityService.submitAct(activityDTO);
        return Result.success();
    }

    @GetMapping("/all")
    public Result getAllActivities(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue = "") String activityName){
        return Result.success(activityService.findAll(new Page<>(pageNum,pageSize),activityName));
    }

    @PostMapping("/pass")
    public Result pass(@RequestParam Integer id){
        activityService.pass(id);
        return Result.success();
    }

    @PostMapping("/reject")
    public Result reject(@RequestParam Integer id){
        activityService.reject(id);
        return Result.success();
    }

    @GetMapping("/myActivity")
    public Result findMyActivity(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize,
                                 @RequestParam(defaultValue = "") String activityName,
                                 @RequestParam Integer uid){
        Page<ActivityDTO> myAct = activityService.findMyAct(new Page<>(pageNum, pageSize), activityName, uid);
        return Result.success(myAct);
    }

    @GetMapping("/getcid")
    public Result getCidByUid(@RequestParam Integer uid){
        return Result.success(activityService.getCid(uid));
    }

    /**
     * 活动资金申请
     */
    @GetMapping("/getOneAct")
    public Result findOneAct(@RequestParam Integer aid){
        Activity oneById = activityService.findOneById(aid);
        ActivityDTO activityDTO = new ActivityDTO();
        BeanUtil.copyProperties(oneById,activityDTO,true);
        return Result.success(activityDTO);
    }

    @PostMapping("/submitMoney")
    public Result MoneySubmit(@RequestBody ActivityDTO activityDTO){
        activityService.moneySubmit(activityDTO);
        return Result.success();
    }

    @GetMapping("/manageMoney")
    public Result MoneyManage(@RequestParam Integer pageNum,
                              @RequestParam Integer pageSize,
                              @RequestParam(defaultValue = "") String activityName){
        return Result.success(activityService.manageMoney(new Page<>(pageNum,pageSize),activityName));
    }

    @PostMapping("/passMoney")
    public Result passMoney(@RequestParam Integer aid){
        activityService.passMoney(aid);
        return Result.success();
    }

    @PostMapping("/rejectMoney")
    public Result rejectMoney(@RequestParam Integer aid){
        activityService.rejectMoney(aid);
        return Result.success();
    }
}
