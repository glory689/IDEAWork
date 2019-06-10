package com.baizhi.serviceImpl;

import com.baizhi.entity.Admin;
import com.baizhi.entity.RoleDto;
import com.baizhi.mapper.AdminMapper;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired(required = false)
    AdminMapper adminMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Admin login(Admin admin) {
        Admin login = adminMapper.login(admin);
        return login;
    }

    @Override
    public Admin selectByUsername(Admin admin) {
        Admin username = adminMapper.selectByUsername(admin);
        return username;
    }

    @Override
    public List<String> getPermission(String roleId) {

        List<String> permission = adminMapper.getPermission(roleId);
        return permission;
    }

    @Override
    public List<RoleDto> getRole(String id) {
        List<RoleDto> role = adminMapper.getRole(id);
        return role;
    }

}
