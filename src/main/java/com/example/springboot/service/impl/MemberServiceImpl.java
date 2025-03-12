package com.example.springboot.service.impl;

import com.example.springboot.entity.Campus;
import com.example.springboot.mapper.MemberMapper;
import com.example.springboot.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YAO
 * @create 2023-02-02 17:03
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;


    @Override
    public void exitMyCampus(Integer uid, Integer cid) {
        memberMapper.insertExit(uid,cid);
    }

    @Override
    public int findMyExit(Integer uid, Integer cid) {
        return memberMapper.countMyExit(uid,cid);
    }
}
