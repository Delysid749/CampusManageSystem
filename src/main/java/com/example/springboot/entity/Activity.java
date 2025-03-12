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
 * @create 2023-02-03 11:16
 */
@TableName("activity")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Activity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String activityName;

    private String activityArea;

    private String activityScale;

    private String activityTime;

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Integer campusId;

    private String campusName;

}
