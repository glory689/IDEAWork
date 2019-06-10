package com.baizhi.test;

import com.baizhi.App;
import com.baizhi.entity.Admin;
import com.baizhi.mapper.AdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class TestCMFZ {

    @Autowired
    AdminMapper adminMapper;

    @Test
    public void testLogin() {
        Admin admin = adminMapper.selectByUsername("admin");
        System.out.println(admin);
    }


}
