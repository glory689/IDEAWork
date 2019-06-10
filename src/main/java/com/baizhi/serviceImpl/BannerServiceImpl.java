package com.baizhi.serviceImpl;

import com.baizhi.entity.Banner;
import com.baizhi.mapper.BannerMapper;
import com.baizhi.service.BannerService;
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
public class BannerServiceImpl implements BannerService {

    @Autowired(required = false)
    BannerMapper bannerMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, Object> selectAllPage(Integer page, Integer rows) {
        //准备返回客户端的数据
        Map<String, Object> maps = new HashMap<>();
        //当前页号
        maps.put("page", page);
        Integer totalCount = bannerMapper.selectTotalCount();
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
        List<Banner> banners = bannerMapper.selectAllByPage(page, rows);
        for (Banner banner : banners) {
            System.out.println("====================" + banner);
        }
        maps.put("rows", banners);
        return maps;
    }

    @Override
    public String insertBanner(Banner banner) {
        banner.setId(UUID.randomUUID().toString().replace("-", ""));
        banner.setCreateDate(new Date());
        bannerMapper.insert(banner);
        return banner.getId();
    }

    @Override
    public void upload(MultipartFile imgPic, HttpSession session, String bannerId) {
        //判断上传的文件夹是否存在
        String realPath = session.getServletContext().getRealPath("/upload/img");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        //文件上传
        String originalFilename = imgPic.getOriginalFilename();
        String str = new Date().getTime() + "" + originalFilename;
        try {
            imgPic.transferTo(new File(realPath, str));
            Banner banner = new Banner();
            banner.setId(bannerId);
            banner.setImgPic(str);
            bannerMapper.updateByPrimaryKeySelective(banner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByIds(String[] id) {
        bannerMapper.deleteByPrimaryKeys(id);
    }

    @Override
    public String update(Banner banner) {
        if (!banner.getImgPic().equals("")) {
            bannerMapper.updateByPrimaryKeySelective(banner);
            return banner.getId();
        } else {
            bannerMapper.updateByPrimaryKeySelective(banner);
            return "";
        }
    }


}
