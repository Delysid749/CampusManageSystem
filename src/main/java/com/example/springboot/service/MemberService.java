package com.example.springboot.service;

import com.example.springboot.entity.Campus;

/**
 * @author YAO
 * @create 2023-02-02 17:02
 */
public interface MemberService {


    void exitMyCampus(Integer uid, Integer cid);

    int findMyExit(Integer uid, Integer cid);
}
