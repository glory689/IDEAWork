package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping("showAll")
    public Map<String, Object> showAll(Integer page, Integer rows) {
        Map<String, Object> map = userService.selectAllPage(page, rows);
        return map;
    }

    @RequestMapping("easyPoiOut")
    public void easyPoiOut(HttpSession session, HttpServletResponse response) throws IOException {
        String realPath = session.getServletContext().getRealPath("/upload/img/");
        List<User> list = userService.getAll();
        for (User user : list) {
            user.setHeadPic(realPath + user.getHeadPic());
        }
        for (User user : list) {
            System.out.println(user);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("这是大标题", "在表格下面显示"),
                User.class, list);
        String encode = URLEncoder.encode(" 150.xls", "UTF-8");
        response.setHeader("content-disposition", "attachment;filename=" + encode);
        workbook.write(response.getOutputStream());
    }


}
