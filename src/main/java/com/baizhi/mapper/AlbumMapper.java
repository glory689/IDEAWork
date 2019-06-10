package com.baizhi.mapper;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumMapper {
    int deleteByPrimaryKeys(String[] id);

    int insert(Album record);

    int insertSelective(Album record);

    Album selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Album record);

    int updateByPrimaryKey(Album record);

    //通过分页进行查询
    public List<Album> selectAllByPage(@Param("page") Integer page, @Param("rows") Integer rows);

    //查询总条数
    public Integer selectTotalCount();

}