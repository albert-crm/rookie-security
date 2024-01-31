package com.rookie.bigdata.mapper;

import com.rookie.bigdata.domain.dto.RoleMenuDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Classname RoleMapperTest
 * @Description TODO
 * @Author rookie
 * @Date 2023/3/14 11:59
 * @Version 1.0
 */

@SpringBootTest
//@ActiveProfiles("uat")
class RoleMapperTest {

    @Autowired
    private RoleMapper roleMapper;

    @Test
    void queryRoleName() {

        Set<String> strings = roleMapper.queryRoleName(1);
        for (String string : strings) {
            System.out.println(string);
        }
    }

    @Test
    void queryRoleMenuDto() {

        List<RoleMenuDto> roleMenuDtos = roleMapper.queryRoleMenuDto();
        for (RoleMenuDto roleMenuDto : roleMenuDtos) {
            System.out.println(roleMenuDto);
        }
    }
}