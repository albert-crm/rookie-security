package com.rookie.bigdata.service.impl;

import com.rookie.bigdata.entity.User;
import com.rookie.bigdata.service.UserSerivce;
import org.springframework.stereotype.Service;

/**
 * @Class UserServiceImpl
 * @Description TODO
 * @Author rookie
 * @Date 2023/12/25 15:42
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserSerivce {
    @Override
    public User login(User user) {

        //从数据库中查询出用户的信息,并比对相关信息是否相同，如用户名，密码等
        User user1=new User();


        return user1;
    }
}
