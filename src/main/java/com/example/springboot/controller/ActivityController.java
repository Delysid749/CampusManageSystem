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
// 活动管理控制器：处理所有与社团活动相关的请求
@RestController
@RequestMapping("/act")
public class ActivityController {

    @Autowired
    private IActivityService activityService;

    // 提交活动申请
    @PostMapping("/submit")
    public Result submit(@RequestBody ActivityDTO activityDTO){
        activityService.submitAct(activityDTO);
        return Result.success();
    }

    // 分页查询所有活动信息
    @GetMapping("/all")
    public Result getAllActivities(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue = "") String activityName){
        return Result.success(activityService.findAll(new Page<>(pageNum,pageSize),activityName));
    }

    // 通过活动申请
    @PostMapping("/pass")
    public Result pass(@RequestParam Integer id){
        activityService.pass(id);
        return Result.success();
    }

    // 拒绝活动申请
    @PostMapping("/reject")
    public Result reject(@RequestParam Integer id){
        activityService.reject(id);
        return Result.success();
    }

    // 查询我的活动列表
    @GetMapping("/myActivity")
    public Result findMyActivity(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize,
                                 @RequestParam(defaultValue = "") String activityName,
                                 @RequestParam Integer uid){
        Page<ActivityDTO> myAct = activityService.findMyAct(new Page<>(pageNum, pageSize), activityName, uid);
        return Result.success(myAct);
    }

    // 根据用户ID获取社团ID
    @GetMapping("/getcid")
    public Result getCidByUid(@RequestParam Integer uid){
        return Result.success(activityService.getCid(uid));
    }

    /**
     * 活动资金申请相关接口
     */

    // 获取单个活动详细信息
    @GetMapping("/getOneAct")
    public Result findOneAct(@RequestParam Integer aid){
        Activity oneById = activityService.findOneById(aid);
        ActivityDTO activityDTO = new ActivityDTO();
        BeanUtil.copyProperties(oneById,activityDTO,true);
        return Result.success(activityDTO);
    }

    // 提交活动资金申请
    @PostMapping("/submitMoney")
    public Result MoneySubmit(@RequestBody ActivityDTO activityDTO){
        activityService.moneySubmit(activityDTO);
        return Result.success();
    }

    // 分页查询活动资金申请列表
    @GetMapping("/manageMoney")
    public Result MoneyManage(@RequestParam Integer pageNum,
                              @RequestParam Integer pageSize,
                              @RequestParam(defaultValue = "") String activityName){
        return Result.success(activityService.manageMoney(new Page<>(pageNum,pageSize),activityName));
    }

    // 通过活动资金申请
    @PostMapping("/passMoney")
    public Result passMoney(@RequestParam Integer aid){
        activityService.passMoney(aid);
        return Result.success();
    }

    // 拒绝活动资金申请
    @PostMapping("/rejectMoney")
    public Result rejectMoney(@RequestParam Integer aid){
        activityService.rejectMoney(aid);
        return Result.success();
    }
}
