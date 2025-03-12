package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.entity.Campus;

/**
 * @author YAO
 * @create 2023-02-03 9:44
 */
public interface SchoolService {

    Page<Campus> findPageFA(Page<Campus> objectPage, String campusName);

    Page<Campus> findPageFUA(Page<Campus> objectPage, String campusName);

    Page<Campus> findDisband(Page<Campus> objectPage, String campusName);

    void rejectDisband(Integer cid, Integer mid);

    void passDisband(Integer cid, Integer mid);
}
