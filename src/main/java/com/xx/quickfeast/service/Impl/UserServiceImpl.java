package com.xx.quickfeast.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xx.quickfeast.entity.User;
import com.xx.quickfeast.mapper.UserMapper;
import com.xx.quickfeast.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
