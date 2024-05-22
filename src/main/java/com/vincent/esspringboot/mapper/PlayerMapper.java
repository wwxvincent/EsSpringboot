package com.vincent.esspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vincent.esspringboot.entity.PlayerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 5/22/24
 * @Description:
 */
@Mapper
public interface PlayerMapper extends BaseMapper<PlayerEntity> {
    List<PlayerEntity> getAllInfoByUser(@Param("id") String id);
}
