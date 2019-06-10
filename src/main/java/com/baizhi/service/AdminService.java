package com.baizhi.service;


import com.baizhi.entity.Admin;
import com.baizhi.entity.RoleDto;

import java.util.List;

public interface AdminService {

    //登陆功能
    public Admin login(Admin admin);

    //根据用户名查询
    public Admin selectByUsername(Admin admin);


    List<String> getPermission(String roleId);

    List<RoleDto> getRole(String id);

    //List<RoleDto> getRole(String id);

    // Object selectByUsername(String principal);
}
