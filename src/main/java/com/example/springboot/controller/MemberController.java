package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author YAO
 * @create 2023-02-02 17:00
 * 社团成员相关接口
 */
@RequestMapping("/member")
@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * 申请退出社团
     * @param uid
     * @return
     */
    @PostMapping("/exit")
    public Result exitMyCampus(@RequestParam Integer uid,@RequestParam Integer cid){
        if(memberService.findMyExit(uid,cid) > 0){
            return Result.error("500","已申请退出该社团");
        }
        memberService.exitMyCampus(uid,cid);
        return Result.success();
    }
}
