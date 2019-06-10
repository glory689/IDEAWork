package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    AlbumService albumService;

    //根据分页展示所有专辑
    @RequestMapping("showAll")
    public Map<String, Object> showAll(Integer page, Integer rows) {
        Map<String, Object> map = albumService.selectAllPage(page, rows);
        return map;
    }

    //添加
    @RequestMapping("edit")
    public Map<String, String> insert(Album album, String oper, String[] id) {
        Map<String, String> map = new HashMap<>();
        System.out.println("控制层的：" + album);
        if (oper.equals("add")) {
            String s = albumService.insertAlbum(album);
            map.put("msg", "添加成功");
            map.put("albumId", s);
        } else if (oper.equals("edit")) {
            System.out.println("编辑后的：" + album);
            String update = albumService.update(album);
            map.put("albumId", update);
            map.put("msg", "修改成功");
        } else if (oper.equals("del")) {
            albumService.deleteByIds(id);
            map.put("msg", "删除成功");
        }
        return map;
    }

    //上传
    @RequestMapping("upload")
    public void upload(MultipartFile coverPic, HttpSession session, String albumId) {
        if (!albumId.equals("")) {
            albumService.upload(coverPic, session, albumId);
        }
    }


}
