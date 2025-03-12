package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author YAO
 * @create 2023-03-04 23:28
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("message")
public class Message {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer campusId;

    private String username;

    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date mesTime;

}
