package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.Map;

public interface ArticleService {

    //展示所有文章
    public Map<String, Object> selectAllPage(Integer page, Integer rows);


    //添加
    public String insert(Article article);

    //删除
    void deleteByIds(String[] id);

    //修改
    public void update(Article article);

}
