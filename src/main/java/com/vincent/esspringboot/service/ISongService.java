package com.vincent.esspringboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vincent.esspringboot.entity.SongEntity;

import java.util.List;

public interface ISongService extends IService<SongEntity> {
    List<SongEntity> selectAll();

}
