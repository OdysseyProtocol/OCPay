package com.stormfives.admin.account.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: lyc
 * Date: 2018/3/17
 * Time: 下午2:08
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;


    @Test
    public void login() throws Exception {

        adminService.login("18721895830","123456");
    }

    @Test
    public void getAdminByName() throws Exception {
    }

    @Test
    public void addAdmin() throws Exception {

        String name ="18721895830";
        String phone = "18721895830";
        String realName = "lyc619";
        String password = "123456";

        adminService.addAdmin(name,password,phone,realName,1);
    }

    @Test
    public void resetPassword() throws Exception {
    }

    @Test
    public void getAdminById() throws Exception {
    }

}