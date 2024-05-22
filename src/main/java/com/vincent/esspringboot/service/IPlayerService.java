package com.vincent.esspringboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vincent.esspringboot.entity.PlayerEntity;

import java.util.List;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 5/22/24
 * @Description:
 */
public interface IPlayerService extends IService<PlayerEntity> {
    List<PlayerEntity> selectAll();

    List<PlayerEntity> selectAllByUser(String id);
}
