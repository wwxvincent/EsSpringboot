package com.vincent.esspringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vincent.esspringboot.mapper.PlayerMapper;
import com.vincent.esspringboot.entity.PlayerEntity;
import com.vincent.esspringboot.service.IPlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 5/22/24
 * @Description:
 */
@Slf4j
@Service
public class PlayerServiceImpl extends ServiceImpl<PlayerMapper, PlayerEntity> implements IPlayerService {

    @Autowired
    private PlayerMapper playerMapper;

    @Override
    public List<PlayerEntity> selectAll() {
        QueryWrapper<PlayerEntity> wrapper = new QueryWrapper<>();
        wrapper.select("*");
        return playerMapper.selectList(wrapper);
    }

    @Override
    public List<PlayerEntity> selectAllByUser(String id) {
        return playerMapper.getAllInfoByUser(id);
    }
}
