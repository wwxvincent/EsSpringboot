package com.vincent.esspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vincent.esspringboot.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
