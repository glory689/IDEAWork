package com.baizhi.service;

import com.baizhi.entity.Album;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface AlbumService {

    //展示所有专辑
    public Map<String, Object> selectAllPage(Integer page, Integer rows);

    //添加
    String insertAlbum(Album album);

    //添加图片
    void upload(MultipartFile coverPic, HttpSession session, String albumId);

    //修改
    public String update(Album album);

    //删除
    void deleteByIds(String[] id);


}
