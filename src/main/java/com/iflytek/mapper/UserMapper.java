package com.iflytek.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iflytek.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    public User getAllRolesByUserId(Integer id);
    public User getUserByUserName(String username);
}
