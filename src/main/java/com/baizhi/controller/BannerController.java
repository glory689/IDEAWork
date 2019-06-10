package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    BannerService bannerService;
    @Autowired(required = false)
    private FastFileStorageClient client;

    //分页展示所有的轮播图
    @RequestMapping("/showAll")
    public @ResponseBody
    Map<String, Object> showAll(Integer page, Integer rows) {
        Map<String, Object> map = bannerService.selectAllPage(page, rows);
        return map;
    }


    //添加
    @RequestMapping("edit")
    public Map<String, String> edit(Banner banner, String oper, String[] id) {
        Map<String, String> map = new HashMap<>();
        System.out.println("dwirybasdhadabn==" + banner);
        if (oper.equals("add")) {
            String s = bannerService.insertBanner(banner);
            map.put("msg", "添加成功");
            map.put("bannerId", s);
        } else if (oper.equals("edit")) {
            System.out.println("编辑后的：" + banner);
            String update = bannerService.update(banner);
            map.put("bannerId", update);
            map.put("msg", "修改成功");
        } else if (oper.equals("del")) {
            bannerService.deleteByIds(id);
            map.put("msg", "删除成功");
        }
        return map;
    }

    //上传
    @RequestMapping("upload")
    public void upload(MultipartFile imgPic, HttpSession session, String bannerId) {
        if (!bannerId.equals("")) {
            bannerService.upload(imgPic, session, bannerId);
        }
    }


}
