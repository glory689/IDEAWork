package com.baizhi.serviceImpl;

import com.baizhi.entity.Album;
import com.baizhi.mapper.AlbumMapper;
import com.baizhi.mapper.ChapterMapper;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumMapper albumMapper;
    @Autowired
    ChapterMapper chapterMapper;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, Object> selectAllPage(Integer page, Integer rows) {
        //准备返回客户端的数据
        Map<String, Object> maps = new HashMap<>();
        //当前页号
        maps.put("page", page);
        Integer totalCount = albumMapper.selectTotalCount();
        //总条数
        maps.put("records", totalCount);
        //总页数
        Integer pageCount = 0;
        //总页数
        if (totalCount % rows != 0) {
            pageCount = totalCount / rows + 1;
        } else {
            pageCount = totalCount / rows;
        }
        maps.put("total", pageCount);
        //当前数据内容
        List<Album> albums = albumMapper.selectAllByPage(page, rows);
        for (Album album1 : albums) {
            System.out.println("" + album1);
        }
        maps.put("rows", albums);
        return maps;
    }

    @Override
    public String insertAlbum(Album album) {
        album.setId(UUID.randomUUID().toString().replace("-", ""));
        //查询子表的数量
        Integer count = chapterMapper.selectTotalCount();
        album.setCount(count);
        album.setCreateDate(new Date());

        albumMapper.insert(album);
        return album.getId();
    }

    @Override
    public void upload(MultipartFile coverPic, HttpSession session, String albumId) {
        //判断上传的文件夹是否存在
        System.out.println("albumId=-===" + albumId);
        String realPath = session.getServletContext().getRealPath("/upload/img");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        //文件上传
        String originalFilename = coverPic.getOriginalFilename();
        String str = new Date().getTime() + "" + originalFilename;
        System.out.println("str====" + str);
        try {
            coverPic.transferTo(new File(realPath, str));
            Album album = new Album();
            album.setId(albumId);
            album.setCoverPic(str);
            albumMapper.updateByPrimaryKeySelective(album);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String update(Album album) {
        if (!album.getCoverPic().equals("")) {
            albumMapper.updateByPrimaryKeySelective(album);
            return album.getId();
        } else {
            albumMapper.updateByPrimaryKeySelective(album);
            return "";
        }
    }

    @Override
    public void deleteByIds(String[] id) {
        albumMapper.deleteByPrimaryKeys(id);
    }


}
