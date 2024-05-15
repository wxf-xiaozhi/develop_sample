package com.sxnd.develop.sample;

import com.sxnd.develop.sample.infrastructure.persistence.dao.UserCrudService;
import com.sxnd.develop.sample.infrastructure.persistence.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName: UserTest
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-05-15 15:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DevelopSampleApplication.class})
public class UserCrudServiceTest {

    @Autowired
    UserCrudService userCrudService;

    @Test
    public void testUserCrud(){
        User user = new User();
        user.setName("wxf");
        user.setAge(10);
        user.setEmail("983053245>qq.com");
        userCrudService.save(user);
    }

}
