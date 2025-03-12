package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.entity.User;

/**
 * @author YAO
 * @create 2023-02-03 9:44
 */
public interface MasterService {
    Page<User> findAllM(Page<Object> objectPage, Integer campusId, String username);

    void removeOneM(Integer campusId, Integer memberId);


    Page<User> findStuForExit(Page<Object> objectPage, Integer cid);

    void passExitAppli(Integer campusId, Integer memberId);

    void rejectExitAppli(Integer campusId, Integer memberId);

    void disbandCampus(Integer cid, Integer mid);

    boolean checkDisband(Integer cid, Integer mid);
}
