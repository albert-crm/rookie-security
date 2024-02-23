package com.rookie.bigdata.mapper;

import com.rookie.bigdata.domain.dto.UserRoleDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Classname UserMapperTest
 * @Description
 * @Author rookie
 * @Date 2023/3/13 17:39
 * @Version 1.0
 */
@SpringBootTest
//@ActiveProfiles("uat")
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void loadUserByUsername() {
    }

    @Test
    void findRoleByUserId() {
    }

    @Test
    void getUserRoleDto() {

        UserRoleDto admin = userMapper.getUserRoleDto("admin");
        System.out.println(admin);
    }
}
