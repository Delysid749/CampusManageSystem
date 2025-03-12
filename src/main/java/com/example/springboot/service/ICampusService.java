package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.dto.CampusDTO;
import com.example.springboot.entity.Campus;
import com.example.springboot.entity.User;

import java.util.List;

/**
 * @author YAO
 * @create 2023-01-13 10:18
 */
public interface ICampusService extends IService<Campus> {

    Page<Campus> findPage(Page<Campus> objectPage, String campusName);

    int createCampus(CampusDTO campusDTO);

    Campus findMyCampus(Integer uid);

    void passAppli(Integer cid,Integer mid);

    int rejectAppli(Integer id);

    int checkJoin(Integer cid,Integer uid);

    int insertJoin(Integer cid,Integer uid);

    List<Campus> myCamJoin(Integer userid);

    int cancelJoinCam(Integer campusId, Integer userId);

    List<Campus> findMycfc(Integer userid);

    int cancelCreateCam(Integer campusId, Integer userId);

    void updateMyCampus(CampusDTO campusDTO);

    Page<User> findStuForJoin(Page<User> object,Integer campusId);

    int rejectJFC(Integer campusId, Integer userId);

    void passJoinC(Integer cid, Integer uid);

    CampusDTO getOne(Integer cid);
}
