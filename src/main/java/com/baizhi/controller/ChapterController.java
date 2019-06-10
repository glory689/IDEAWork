package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    //根据分页展示所有
    @RequestMapping("showAll")
    public Map<String, Object> showAll(Integer page, Integer rows, String albumId) {
        Map<String, Object> map = chapterService.selectAllPage(page, rows, albumId);
        return map;
    }


    //添加
    @RequestMapping("edit")
    public Map<String, String> insert(Chapter chapter, String oper, String albumId, String[] id) {
        Map<String, String> map = new HashMap<>();
        if (oper.equals("add")) {
            System.out.println("上传的控制层：" + chapter);
            System.out.println("上传的控制层" + albumId);
            String s = chapterService.insertChapter(chapter, albumId);
            map.put("chapterId", s);
            map.put("msg", "添加成功");
        } else if (oper.equals("edit")) {
            System.out.println();
            String update = chapterService.update(chapter);
            map.put("chapterId", update);
            map.put("msg", "修改成功");
        } else if (oper.equals("del")) {
            chapterService.deleteByIds(id);
            map.put("msg", "删除成功");
        }
        return map;
    }

    //上传
    @RequestMapping("upload")
    public Map<String, String> upload(MultipartFile audio, HttpSession session, String chapterId) {
        chapterService.uploadChapter(audio, session, chapterId);
        return null;
    }

    //下载
    @RequestMapping("downLoadAudio")
    public void downLoadAudio(String audioName, HttpServletResponse response, HttpSession session) {
        chapterService.downLoadAudio(audioName, response, session);
    }

}
