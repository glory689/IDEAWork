package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;


    @RequestMapping("showAll")
    public Map<String, Object> showAll(Integer page, Integer rows) {
        Map<String, Object> map = articleService.selectAllPage(page, rows);
        return map;
    }

    @RequestMapping("edit")
    public Map<String, String> edit(Article article, String oper, String[] id) {
        Map<String, String> map = new HashMap<>();
        articleService.insert(article);
        return map;
    }

    @RequestMapping("delete")
    public Map<String, String> delete(String oper, String[] id) {
        Map<String, String> map = new HashMap<>();
        if ("del".equals(oper)) {
            articleService.deleteByIds(id);
            map.put("msg", "ok");
        }
        return map;
    }

    @RequestMapping("uplade")
    public void update(Article article) {

        articleService.update(article);
    }

}
