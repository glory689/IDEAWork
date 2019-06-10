package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {


    public Map<String, Object> selectAllPage(Integer page, Integer rows);

    List<User> getAll();
}
