package com.baizhi.serviceImpl;

import com.baizhi.entity.Article;
import com.baizhi.mapper.ArticleMapper;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, Object> selectAllPage(Integer page, Integer rows) {
        //准备返回客户端的数据
        Map<String, Object> maps = new HashMap<>();
        //当前页号
        maps.put("page", page);
        Integer totalCount = articleMapper.selectTotalCount();
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
        List<Article> articles = articleMapper.selectAllByPage(page, rows);
        for (Article article : articles) {
            System.out.println("article业务层：" + article);
        }
        maps.put("rows", articles);
        return maps;
    }

    @Override
    public String insert(Article article) {
        article.setId(UUID.randomUUID().toString().replace("-", ""));
        article.setCreateDate(new Date());
        articleMapper.insert(article);
        return article.getId();
    }

    @Override
    public void deleteByIds(String[] id) {
        articleMapper.deleteByKeys(id);
    }

    @Override
    public void update(Article article) {
        articleMapper.updateByPrimaryKeySelective(article);

    }
}
