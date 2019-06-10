package com.baizhi.mapper;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterMapper {
    //删除
    int deleteByKeys(String[] id);

    //添加
    int insert(Chapter record);

    //修改标题
    int update(Chapter record);

    Chapter selectByPrimaryKey(String albumId);

    //修改音频
    int updateByPrimaryKey(Chapter record);

    //通过分页进行查询
    public List<Chapter> selectAllByPage(@Param("page") Integer page, @Param("rows") Integer rows, @Param("albumId") String albumId);

    //查询总条数
    public Integer selectTotalCount();


}