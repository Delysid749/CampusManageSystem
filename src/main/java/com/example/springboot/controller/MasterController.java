package com.example.springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.service.ICampusService;
import com.example.springboot.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author YAO
 * @create 2023-02-03 9:37
 */
@RestController
@RequestMapping("/master")
public class MasterController {

    @Autowired
    private MasterService masterService;

    @Autowired
    @Qualifier("cam")
    private ICampusService campusService;

    /**
     * 面向社长的社团成员管理
     */
    @GetMapping("/findallmember")
    public Result findAllMember(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam Integer masterId,
                                @RequestParam(defaultValue = "") String username){
        Integer campusId = campusService.findMyCampus(masterId).getId();
        return Result.success(masterService.findAllM(new Page<>(pageNum, pageSize),campusId,username));
    }

    /**
     * 将成员踢出社团
     */
    @PostMapping("/removemember")
    public Result removeOneMember(@RequestParam Integer campusId
            ,@RequestParam Integer memberId){
        masterService.removeOneM(campusId,memberId);
        return Result.success();
    }

    /**
     * 申请解散社团
     */
    @PostMapping("/disband")
    public Result disBandMyCampus(@RequestParam Integer cid,@RequestParam Integer mid){
        if(masterService.checkDisband(cid,mid)){
            return Result.error("500","已申请解散该社团");
        }
        masterService.disbandCampus(cid,mid);
        return Result.success();
    }

    /**
     * 拒绝加入社团申请
     */
    @PostMapping("/rejectjoin")
    public Result rejectJoinForCampus(@RequestParam Integer cid,@RequestParam Integer uid){
        campusService.rejectJFC(cid,uid);
        return Result.success();
    }

    /**
     * 通过加入社团申请
     */
    @PostMapping("/passjoin")
    public Result passJoinForCampus(@RequestParam Integer cid,@RequestParam Integer uid){
        campusService.passJoinC(cid,uid);
        return Result.success();
    }

    /**
     * 申请加入我的社团的人
     */
    @GetMapping("/appforjoin")
    public Result findMyAppForJoin(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam Integer masterId){
        Integer cid = campusService.findMyCampus(masterId).getId();
        return Result.success(campusService.findStuForJoin(new Page<>(pageNum, pageSize),cid));
    }

    /**
     * 退出社团申请的人
     * @param pageNum
     * @param pageSize
     * @param masterId
     * @return
     */
    @GetMapping("/allExits")
    public Result allExits(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam Integer masterId){

        Integer cid = campusService.findMyCampus(masterId).getId();
        return Result.success(masterService.findStuForExit(new Page<>(pageNum, pageSize),cid));
    }

    @PostMapping("/passExit")
    public Result passExit(@RequestParam Integer campusId
            ,@RequestParam Integer memberId){
        masterService.passExitAppli(campusId,memberId);
        return Result.success();
    }

    @PostMapping("/rejectExit")
    public Result rejectExit(@RequestParam Integer campusId
            ,@RequestParam Integer memberId){
        masterService.rejectExitAppli(campusId,memberId);
        return Result.success();
    }


}
