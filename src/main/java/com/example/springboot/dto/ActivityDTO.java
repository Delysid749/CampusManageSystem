package com.example.springboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author YAO
 * @create 2023-02-07 10:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDTO {

    private Integer id;

    private String activityName;

    private String activityArea;

    private String activityScale;

    private String activityTime;

    private Integer status;

    private Integer campusId;

    private String campusName;

    private String moneyAcount;

    private String moneyUse;

    private int moneyStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
