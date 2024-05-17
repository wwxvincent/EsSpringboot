package com.vincent.esspringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vincent.esspringboot.entity.SongEntity;
import com.vincent.esspringboot.entity.UserEntity;
import com.vincent.esspringboot.mapper.SongMapper;
import com.vincent.esspringboot.mapper.UserMapper;
import com.vincent.esspringboot.service.ISongService;
import com.vincent.esspringboot.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserEntity> selectAll() {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.select("*");
        return userMapper.selectList(wrapper);
    }
}
