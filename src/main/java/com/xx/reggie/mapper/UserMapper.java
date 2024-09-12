package com.xx.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xx.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
