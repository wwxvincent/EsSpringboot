package com.vincent.esspringboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vincent.esspringboot.entity.UserEntity;

import java.util.List;

public interface IUserService extends IService<UserEntity> {
    List<UserEntity> selectAll();
}
