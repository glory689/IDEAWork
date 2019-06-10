package com.baizhi.service;

import com.baizhi.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface BannerService {

    //展示所有轮播图
    public Map<String, Object> selectAllPage(Integer page, Integer rows);

    //添加轮播图
    String insertBanner(Banner banner);

    //根据id添加图片
    void upload(MultipartFile imgPic, HttpSession session, String bannerId);

    //删除
    void deleteByIds(String[] id);

    //修改
    public String update(Banner banner);


}
