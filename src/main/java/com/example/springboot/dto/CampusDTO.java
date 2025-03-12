package com.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YAO
 * @create 2023-01-16 10:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampusDTO {

    private Integer userId;

    private String userName;

    private String campusName;

    private String campusScale;

    private String campusInfo;

    private String masterName;

    private Integer campusId;

    private String message;
}
