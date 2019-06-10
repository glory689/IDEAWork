package com.baizhi.serviceImpl;

import com.baizhi.entity.User;
import com.baizhi.mapper.UserMapper;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, Object> selectAllPage(Integer page, Integer rows) {
        //准备返回客户端的数据
        Map<String, Object> maps = new HashMap<>();
        //当前页号
        maps.put("page", page);
        Integer totalCount = userMapper.selectTotalCount();
        //总条数
        maps.put("records", totalCount);
        //总页数
        Integer pageCount = 0;
        //总页数
        if (totalCount % rows != 0) {
            pageCount = totalCount / rows + 1;
        } else {
            pageCount = totalCount / rows;
        }
        maps.put("total", pageCount);
        //当前数据内容
        List<User> users = userMapper.selectAllByPage(page, rows);
        for (User user : users) {
            System.out.println("user业务层中：" + user);
        }
        maps.put("rows", users);
        return maps;
    }

    @Override
    public List<User> getAll() {
        List<User> all = userMapper.getAll();
        return all;
    }
}
