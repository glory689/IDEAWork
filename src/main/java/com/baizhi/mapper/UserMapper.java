package com.baizhi.mapper;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByKeys(String[] id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //通过分页进行查询
    public List<User> selectAllByPage(@Param("page") Integer page, @Param("rows") Integer rows);

    //查询总条数
    public Integer selectTotalCount();

    List<User> getAll();


}