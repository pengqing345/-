package com.pq.admin;

import com.pq.pojo.User;
import com.pq.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminApplicationTests {

    @Autowired
    AdminService adminService;

    @Test
    public void contextLoads() {
        User u = new User();
        u.setUserName("admin");
        u.setPassword("0192023a7bbd73250516f069df18b500");
        System.out.println(adminService.admin(u));
    }

}
