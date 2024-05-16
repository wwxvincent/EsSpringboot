package com.vincent.esspringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vincent.esspringboot.entity.SongEntity;
import com.vincent.esspringboot.mapper.SongMapper;
import com.vincent.esspringboot.repository.SongRepository;
import com.vincent.esspringboot.service.ISongService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SongServiceImpl extends ServiceImpl<SongMapper, SongEntity> implements ISongService {

    @Autowired
    private SongMapper songMapper;

    @Autowired
//    private SongRepository songRepository;

    @Override
    public List<SongEntity> selectAll() {
        QueryWrapper<SongEntity> wrapper = new QueryWrapper<>();
        wrapper.select("*");
        return songMapper.selectList(wrapper);
    }

//    public List<SongEntity> searchByFuzzyName(String keyword) {
//        return songRepository.findByNameFuzzy(keyword);
//    }


}
