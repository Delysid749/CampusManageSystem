package com.example.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * 社长数据访问层接口
 * 处理社团社长相关的数据库操作
 *
 * @author YAO
 * @create 2023-02-03 9:45
 */
public interface MasterMapper {
    /**
     * 踢出社团成员
     *
     * @param campusId 社团ID
     * @param memberId 成员ID
     */
    @Delete("delete from member where campus_id =#{campusId} and member_id =#{memberId}")
    void deleteOneMember(Integer campusId, Integer memberId);

    /**
     * 查询所有退出申请
     *
     * @param objectPage 分页对象
     * @param cid 社团ID
     * @return 退出申请的用户列表
     */
    Page<User> findAllExits(Page<Object> objectPage, Integer cid);

    /**
     * 删除退出社团申请记录
     *
     * @param campusId 社团ID
     * @param memberId 成员ID
     */
    @Delete("delete from campus_exit where campus_id =#{campusId} and user_id =#{memberId}")
    void deleteExit(Integer campusId, Integer memberId);

    /**
     * 申请解散社团
     *
     * @param cid 社团ID
     * @param mid 社长ID
     */
    @Insert("insert into campus_disband(campus_id,master_id) values (#{cid},#{mid})")
    void disband(Integer cid, Integer mid);

    /**
     * 检查是否存在解散申请
     *
     * @param cid 社团ID
     * @param mid 社长ID
     * @return 存在返回1，不存在返回0
     */
    @Select("select count(*) from campus_disband where campus_id =#{cid} and master_id =#{mid}")
    int checkDis(Integer cid,Integer mid);
}
