package com.example.springboot.mapper;

import com.example.springboot.entity.Campus;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * @author YAO
 * @create 2023-02-02 17:06
 */
public interface MemberMapper {

    Campus myCampus(Integer uid);

    @Insert("insert into campus_exit(campus_id,user_id) values (#{cid},#{uid})")
    void insertExit(Integer uid, Integer cid);

    @Select("select count(*) from campus_exit where campus_id =#{cid} and user_id =#{uid}")
    int countMyExit(Integer uid, Integer cid);
}
