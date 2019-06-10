package com.baizhi.mapper;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerMapper {
    int deleteByPrimaryKeys(String[] id);

    int insert(Banner banner);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Banner banner);

    int updateByPrimaryKey(Banner record);

    //通过分页进行查询
    public List<Banner> selectAllByPage(@Param("page") Integer page, @Param("rows") Integer rows);

    //查询总条数
    public Integer selectTotalCount();


}