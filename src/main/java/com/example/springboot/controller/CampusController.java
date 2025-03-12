package com.example.springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.dto.CampusDTO;
import com.example.springboot.entity.Campus;
import com.example.springboot.service.ICampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YAO
 * @create 2023-01-13 10:15
 */
// 社团管理控制器：处理所有与社团相关的请求
@RestController
@RequestMapping("/campus")
public class CampusController {

    @Autowired
    @Qualifier("cam")
    private ICampusService campusService;

    @Autowired
    @Qualifier("testCam")
    private ICampusService campusService1;

    // 分页查询社团信息
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String campusName) {
        return Result.success(campusService.findPage(new Page<>(pageNum, pageSize), campusName));
    }

    /**
     * 成立社团
     * @param campusDTO
     * @return
     */
    @PostMapping("/create")
    public Result createCam(@RequestBody CampusDTO campusDTO){
        campusService.createCampus(campusDTO);
        return Result.success();
    }

    // 更新社团信息
    @PostMapping("/updatecampus")
    public Result updateMyCampus(@RequestBody CampusDTO campusDTO){
        campusService.updateMyCampus(campusDTO);
        return Result.success();
    }

    // 查询我的社团信息
    @GetMapping("/mycampus")
    public Result myCampus(@RequestParam Integer uid){
        return Result.success(campusService.findMyCampus(uid));
    }

    /**
     * 通过社团成立审核
     * @param cid    申请的社团id
     * @param mid    申请人id
     * @return
     */
    @PostMapping("/passappli")
    public Result passApplication(@RequestParam Integer cid,@RequestParam Integer mid){
        campusService.passAppli(cid,mid);
        return Result.success();
    }

    /**
     * 拒绝社团成立审核
     * @param id
     * @return
     */
    @PostMapping("/rejectappli")
    public Result rejectApplication(@RequestParam Integer id){
        campusService.rejectAppli(id);
        return Result.success();
    }

    /**
     * 检查是否已申请加入该社团
     * @param campusId
     * @param userId
     * @return
     */
    @GetMapping("/checkjoin")
    public boolean checkJoinCampus(@RequestParam Integer campusId,@RequestParam Integer userId){
        int i = campusService.checkJoin(campusId, userId);
        if(i > 0){
            return false;
        }
        return true;
    }

    /**
     * 申请加入社团
     * @param campusId
     * @param userId
     * @return
     */
    @PostMapping("joincampus")
    public Result joinCampus(@RequestParam Integer campusId,@RequestParam Integer userId){
        if(checkJoinCampus(campusId,userId)){
            campusService.insertJoin(campusId,userId);
            return Result.success();
        }else{
            return Result.error("500","已申请加入该社团");
        }
    }

    // 查询我申请加入的社团列表
    @GetMapping("/mycjoin")
    public Result MyCamJoin(@RequestParam Integer userid){
        return Result.success(campusService.myCamJoin(userid));
    }

    // 取消加入社团申请
    @PostMapping("canceljoin")
    public Result cancelJoinCam(@RequestParam Integer campusId,@RequestParam Integer userId){
        campusService.cancelJoinCam(campusId,userId);
        return Result.success();
    }

    // 查询我的社团成立申请
    @GetMapping("findmycc")
    public Result findMyCreateForCampus(@RequestParam Integer userid){
        return Result.success(campusService.findMycfc(userid));
    }

    // 取消社团成立申请
    @PostMapping("cancelcreate")
    public Result cancelCreateCam(@RequestParam Integer campusId,@RequestParam Integer userId){
        campusService.cancelCreateCam(campusId,userId);
        return Result.success();
    }

    // 根据ID查询社团详细信息
    @GetMapping("/getOneCampus")
    public Result getOneCampus(@RequestParam Integer cid){
        return Result.success(campusService.getOne(cid));
    }
}
