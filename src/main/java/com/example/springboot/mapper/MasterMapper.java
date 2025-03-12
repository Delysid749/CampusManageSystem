package com.example.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * @author YAO
 * @create 2023-02-03 9:45
 */
public interface MasterMapper {
    /**
     * 踢出社团
     * @param campusId
     * @param memberId
     */
    @Delete("delete from member where campus_id =#{campusId} and member_id =#{memberId}")
    void deleteOneMember(Integer campusId, Integer memberId);

    Page<User> findAllExits(Page<Object> objectPage, Integer cid);

    /**
     * 删除退出社团申请表
     * @param campusId
     * @param memberId
     */
    @Delete("delete from campus_exit where campus_id =#{campusId} and user_id =#{memberId}")
    void deleteExit(Integer campusId, Integer memberId);

    /**
     * 申请解散社团
     * @param cid
     * @param mid
     */
    @Insert("insert into campus_disband(campus_id,master_id) values (#{cid},#{mid})")
    void disband(Integer cid, Integer mid);

    @Select("select count(*) from campus_disband where campus_id =#{cid} and master_id =#{mid}")
    int checkDis(Integer cid,Integer mid);
}
