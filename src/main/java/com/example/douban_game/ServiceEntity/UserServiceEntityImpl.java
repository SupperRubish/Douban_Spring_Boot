package com.example.douban_game.ServiceEntity;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.douban_game.dao.UserMapper;
import com.example.douban_game.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceEntityImpl extends ServiceImpl<UserMapper, User> implements UserServiceEntity {
}
