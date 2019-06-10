package com.baizhi.shiro;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ShiroFileConf {
    @Bean  //
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        HashMap<String, String> map = new HashMap<>();
        //匿名资源必须放在认证资源之上
        map.put("login/**", "anon");
        map.put("/code/getCode", "anon");
        map.put("admin/login", "anon");
        map.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        shiroFilterFactoryBean.setLoginUrl("/login/login.jsp");
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(MyCmfzRealm myCmfzRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        CacheManager cache = new MemoryConstrainedCacheManager();
        securityManager.setCacheManager(cache);
        securityManager.setRealm(myCmfzRealm);
        return securityManager;
    }

    @Bean
    public MyCmfzRealm myCmfzRealm() {
        MyCmfzRealm myCmfzRealm = new MyCmfzRealm();
        return myCmfzRealm;
    }
}
