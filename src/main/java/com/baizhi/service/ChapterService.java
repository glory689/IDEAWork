package com.baizhi.service;

import com.baizhi.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public interface ChapterService {

    //展示所有子表
    public Map<String, Object> selectAllPage(Integer page, Integer rows, String albumId);

    //添加
    String insertChapter(Chapter chapter, String albumId);

    //上传
    void uploadChapter(MultipartFile audio, HttpSession session, String chapterId);

    //下载
    void downLoadAudio(String audioName, HttpServletResponse response, HttpSession session);

    //修改标题
    String update(Chapter chapter);

    //删除
    void deleteByIds(String[] id);
}
