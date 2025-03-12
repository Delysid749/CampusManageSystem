package com.example.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.entity.Campus;
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author YAO
 * @create 2023-02-03 9:45
 */
public interface SchoolMapper {

    Page<Campus> findPageFA(Page<Campus> page, @Param("campusName") String campusName);

    Page<Campus> findPageFUA(Page<Campus> page, @Param("campusName") String campusName);

    Page<Campus> findAllDisbind(Page<Campus> page, String campusName);

    @Delete("delete from campus_disband where campus_id =#{cid} and master_id =#{mid}")
    void deleteDisband(Integer cid, Integer mid);

    List<User> findDisbandMember(Integer cid);

    @Delete("delete from campus_exit where campus_id =#{cid}")
    void deleteCampusExit(Integer cid);

    @Delete("delete from campus_join where campus_id =#{cid}")
    void deleteCampusJoin(Integer cid);

    @Delete("delete from member where campus_id =#{cid}")
    void deleteMember(Integer cid);
}
