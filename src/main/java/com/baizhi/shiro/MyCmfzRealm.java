package com.baizhi.shiro;

import com.baizhi.entity.Admin;
import com.baizhi.entity.RoleDto;
import com.baizhi.service.AdminService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MyCmfzRealm extends AuthorizingRealm {
    @Autowired
    private AdminService adminService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Admin primaryPrincipal = (Admin) principalCollection.getPrimaryPrincipal();
        Admin admin = adminService.selectByUsername(primaryPrincipal);
        SimpleAuthorizationInfo authorizationInfo = null;
        if (admin != null) {
            List<RoleDto> roles = adminService.getRole(admin.getId());
            ArrayList<String> objects = new ArrayList<>();
            HashSet<String> hashSet = new HashSet<>();
            for (RoleDto role : roles) {
                objects.add(role.getRoleName());
                String roleId = role.getId();
                List<String> permission = adminService.getPermission(roleId);
                for (String s : permission) {
                    hashSet.add(s);
                }
                authorizationInfo = new SimpleAuthorizationInfo();
                authorizationInfo.addStringPermissions(hashSet);
                authorizationInfo.addRoles(objects);
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        Admin principal = (Admin) authenticationToken.getPrincipal();
        Admin admin = adminService.selectByUsername(principal);
        AuthenticationInfo authenticationInfo = null;
        if (admin != null) {
            authenticationInfo = new SimpleAuthenticationInfo(admin.getUsername(), admin.getPassword(), this.getName());
        }
        return authenticationInfo;
    }
}
