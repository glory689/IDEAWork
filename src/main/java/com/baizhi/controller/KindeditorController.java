package com.baizhi.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/kindeditor")
public class KindeditorController {


    @RequestMapping("uploadImg")
    public Map<String, Object> uploadImg(HttpServletRequest request, MultipartFile img) {
        System.out.println("34534534534=====" + img);
        Map<String, Object> map = new HashMap<>();
        //获取图片路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/img");
        //根据图片路径获得文件
        File file = new File(realPath);
        //判断文件夹是否存在
        if (!file.exists()) {
            //没有则创建
            file.mkdirs();
        }
        //根据图片获取图片名
        String originalFilename = img.getOriginalFilename();
        //进行拆分
        String s = new Date().getTime() + "_" + originalFilename;

        //
        try {
            img.transferTo(new File(realPath, s));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         *  {"error":0,"url":"http://localhost:9999/cmfz/upload/img/xxx"}
         */
        map.put("error", 0);
        // 获取http协议
        String scheme = request.getScheme();

        //获取localhost
        InetAddress localHost = null;
        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String s1 = localHost.toString();
        //进行拆分   获取下标为1的数据
        String localhost = s1.split("/")[1];
        //获取端口9999
        int serverPort = request.getServerPort();
        // 获取项目名/cmfz
        String contextPath = request.getContextPath();
        //进行拼接
        String url = scheme + "://" + localhost + ":" + serverPort + contextPath + "/upload/img/" + s;
        map.put("url", url);
        return map;
    }

    /**
     *
     *
     */
    @RequestMapping("getAll")
    public Map<String, Object> getAll(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        String realPath = request.getSession().getServletContext().getRealPath("/upload/img");

        File file = new File(realPath);

        ArrayList<Object> list = new ArrayList<>();
        String[] imgs = file.list();//所有的图片对象
        for (String img : imgs) {
            HashMap<String, Object> ma = new HashMap<>();
            ma.put("is_dir", false);
            ma.put("has_file", false);
            /**
             *  http://localhost:9999/cmfz/upload/img/lunbuto.png
             */
            //获去图片的大小
            File file1 = new File(realPath, img);
            long length = file1.length();
            ma.put("filesize", length);//图片的大小
            ma.put("dir_path", "");
            ma.put("is_photo", true);
            //图片的格式  jpg | png | ...
            String extension = FilenameUtils.getExtension(img);
            ma.put("filetype", extension);
            //获取时间戳
            ma.put("filename", img);
           /* String s = img.split("_")[0];
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            System.out.println("!!!!!!!!!!!"+s);
            //去空   Integer.parseInt(s.trim());
            Long.parseLong(s.trim());
            long lt = new Long(s);
            System.out.println("222222222222"+lt);
            Date date = new Date(lt);
            String format = simpleDateFormat.format(date);*/
            ma.put("datetime", new Date());//字符串的时间
            list.add(ma);
        }
        map.put("file_list", list);
        map.put("moveup_dir_path", "");
        // http
        String scheme = request.getScheme();
        //localhost
        InetAddress localHost = null;
        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String s1 = localHost.toString();
        String localhost = s1.split("/")[1];
        //9999
        int serverPort = request.getServerPort();
        // /cmfz
        String contextPath = request.getContextPath();
        String url = scheme + "://" + localhost + ":" + serverPort + contextPath + "/upload/img/";
        //http://localhost:9999/cmfz/upload/img/
        map.put("current_url", url);
        int length = imgs.length;
        //图片的总数量
        map.put("total_count", length);
        return map;

    }

    /**
     *
     *
     */
    @RequestMapping("insert")
    public void addKindeditor(String content) {
        System.out.println(content);
    }


}
