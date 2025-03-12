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
// 社团社长管理控制器：处理所有与社长管理相关的请求
@RestController
@RequestMapping("/master")
public class MasterController {

    @Autowired
    private MasterService masterService;

    @Autowired
    @Qualifier("cam")
    private ICampusService campusService;

    /**
     * 查询社团所有成员信息
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
     * 将成员从社团中移除
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
     * 拒绝社团加入申请
     */
    @PostMapping("/rejectjoin")
    public Result rejectJoinForCampus(@RequestParam Integer cid,@RequestParam Integer uid){
        campusService.rejectJFC(cid,uid);
        return Result.success();
    }

    /**
     * 通过社团加入申请
     */
    @PostMapping("/passjoin")
    public Result passJoinForCampus(@RequestParam Integer cid,@RequestParam Integer uid){
        campusService.passJoinC(cid,uid);
        return Result.success();
    }

    /**
     * 查询申请加入社团的成员列表
     */
    @GetMapping("/appforjoin")
    public Result findMyAppForJoin(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam Integer masterId){
        Integer cid = campusService.findMyCampus(masterId).getId();
        return Result.success(campusService.findStuForJoin(new Page<>(pageNum, pageSize),cid));
    }

    /**
     * 查询申请退出社团的成员列表
     */
    @GetMapping("/allExits")
    public Result allExits(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam Integer masterId){

        Integer cid = campusService.findMyCampus(masterId).getId();
        return Result.success(masterService.findStuForExit(new Page<>(pageNum, pageSize),cid));
    }

    /**
     * 通过退出社团申请
     */
    @PostMapping("/passExit")
    public Result passExit(@RequestParam Integer campusId
            ,@RequestParam Integer memberId){
        masterService.passExitAppli(campusId,memberId);
        return Result.success();
    }

    /**
     * 拒绝退出社团申请
     */
    @PostMapping("/rejectExit")
    public Result rejectExit(@RequestParam Integer campusId
            ,@RequestParam Integer memberId){
        masterService.rejectExitAppli(campusId,memberId);
        return Result.success();
    }


}
