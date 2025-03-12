package com.example.springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author YAO
 * @create 2023-02-03 9:40
 */
// 学校管理控制器：处理所有与学校管理相关的请求
@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    /**
     * 社团成立申请管理
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @param campusName 社团名称（可选）
     * @return 分页结果
     */
    @GetMapping("/pageforapplication")
    public Result findPageForApplication(@RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize,
                                         @RequestParam(defaultValue = "") String campusName) {

        return Result.success(schoolService.findPageFA(new Page<>(pageNum, pageSize), campusName));

    }

    /**
     * 查找审核不通过的社团
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @param campusName 社团名称（可选）
     * @return 分页结果
     */
    @GetMapping("/pageforunapplication")
    public Result findPageForUnApplication(@RequestParam Integer pageNum,
                                           @RequestParam Integer pageSize,
                                           @RequestParam(defaultValue = "") String campusName) {

        return Result.success(schoolService.findPageFUA(new Page<>(pageNum, pageSize), campusName));

    }

    /**
     * 社团解散管理
     * @return 分页结果
     */
    @GetMapping("/allDisband")
    public Result disbandManage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String campusName) {
        return Result.success(schoolService.findDisband(new Page<>(pageNum,pageSize),campusName));
    }

    /**
     * 通过解散社团申请
     * @return
     */
    @PostMapping("/passDisband")
    public Result passDis(@RequestParam Integer cid,@RequestParam Integer mid){
        schoolService.passDisband(cid,mid);
        return Result.success();
    }

    /**
     * 拒绝解散社团申请
     * @return
     */
    @PostMapping("/rejectDisband")
    public Result rejectDis(@RequestParam Integer cid,@RequestParam Integer mid){
        schoolService.rejectDisband(cid,mid);
        return Result.success();
    }
}
