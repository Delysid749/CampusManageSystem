package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.dto.UserDTO;
import com.example.springboot.dto.UserPasswordDTO;
import com.example.springboot.entity.User;

/**
 * <p>
 *  服务类
 * </p>

 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User simRegister(UserDTO userDTO);

    User schRegister(UserDTO userDTO);

    void updatePassword(UserPasswordDTO userPasswordDTO);

    void changeRoleToM(Integer userid,String role);

    Page<User> findPage(Page<User> objectPage, String username, String email, String address);

}
