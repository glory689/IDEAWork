package com.baizhi.mapper;

import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    int deleteByKeys(String[] id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    //通过分页进行查询
    public List<Article> selectAllByPage(@Param("page") Integer page, @Param("rows") Integer rows);

    //查询总条数
    public Integer selectTotalCount();


}