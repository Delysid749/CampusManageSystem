package com.example.springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.dto.CampusDTO;
import com.example.springboot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author YAO
 * @create 2023-03-04 23:31
 */
// 消息管理控制器：处理所有与社团消息相关的请求
@RestController
@RequestMapping("/mes")
public class MessageController {

    @Autowired
    private MessageService messageService;

    // 保存社团消息
    @PostMapping("/saveMess")
    public Result saveMessage(@RequestBody CampusDTO campusDTO){
        messageService.save(campusDTO);
        return Result.success();
    }

    // 分页查询社团消息列表
    @GetMapping("/showMes")
    public Result showMessage(@RequestParam Integer pageNum,
                              @RequestParam Integer pageSize,
                              @RequestParam Integer cid){

        return Result.success(messageService.showMessages(new Page<>(pageNum, pageSize),cid));
    }
}
