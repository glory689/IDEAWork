package com.baizhi.mapper;

import com.baizhi.entity.Admin;
import com.baizhi.entity.RoleDto;

import java.util.List;

public interface AdminMapper {


    public Admin login(Admin admin);

    //根据用户名和密码查询
    public Admin selectByUsername(Admin admin);

    Admin selectByUsername(String username);

    List<RoleDto> getRole(String aid);

    List<String> getPermission(String rid);


}
