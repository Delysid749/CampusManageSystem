package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.dto.CampusDTO;
import com.example.springboot.entity.Campus;
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author YAO
 * @create 2023-01-13 10:19
 */
public interface CampusMapper extends BaseMapper<Campus> {

    Page<Campus> findPage(Page<Campus> page, @Param("campusName") String campusName);

    int createNew(CampusDTO campusDTO);

    @Update("update campus set status = 1 where id =#{id}")
    int passApplication(Integer id);

    @Update("update campus set status = 2 where id =#{id}")
    int rejectApplication(Integer id);

    @Select("select count(*) from campus_join where campus_id =#{cid} and user_id =#{uid}")
    int checkJoinCampus(Integer cid,Integer uid);

    @Insert("insert into campus_join(campus_id,user_id) VALUES(#{cid},#{uid})")
    int insertJoin(Integer cid,Integer uid);

    List<Campus> myJoinForCampus(Integer userid);

    @Delete("delete from campus_join where campus_id =#{campusId} and user_id =#{userId}")
    int cancelJC(Integer campusId, Integer userId);


    List<Campus> findMyCFC(Integer userid);

    @Delete("delete from campus where id =#{campusId} and master_id =#{userId}")
    int cancelCC(Integer campusId, Integer userId);

    @Update("update campus set campus_name =#{campusName},campus_scale =#{campusScale},campus_info" +
            " =#{campusInfo} where id = #{campusId}")
    void updateCampus(CampusDTO campusDTO);

    Page<User> findStuForJoin(Page<User> object, Integer campusId);

    @Insert("insert into member(campus_id,member_id) values (#{cid},#{uid})")
    int addMember(Integer cid, Integer uid);

    Page<User> findAllMember(Page<Object> objectPage, Integer campusId, String username);

    /**
     *     删除审核中的社团成立申请
     * @param mid
     */
    @Delete("delete from campus where master_id =#{mid} and status = 0")
    void removeZeroCampus(Integer mid);

    /**
     * 写入社长-社团表相关信息
     * @param mid
     * @param cid
     */
    @Insert("insert into master_campus(master_id,campus_id) values (#{mid},#{cid})")
    void addMCrelation(Integer mid,Integer cid);

    /**
     * 判断是否存在未审核的成立社团申请
     */
    @Select("select * from campus where master_id =#{mid} and status = 0")
    List<Campus> findMyZeroCampus(Integer mid);

    /**
     * 判断是否存在申请加入社团
     */
    @Select("select count(*) from campus_join where user_id =#{uid}")
    int checkJoin(Integer uid);

    /**
     * 根据userid删除加入社团申请
     */
    @Delete("delete from campus_join where user_id =#{uid}")
    void delMyJoin(Integer uid);

    /**
     * 删除master_campus表数据
     */
    @Delete("delete from master_campus where master_id =#{mid}")
    void delMasCam(Integer mid);

    @Select("select * from campus where id =#{cid}")
    Campus getOne(Integer cid);
}
