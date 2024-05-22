package com.vincent.esspringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vincent.esspringboot.entity.UserEntity;
import com.vincent.esspringboot.mapper.UserMapper;
import com.vincent.esspringboot.repository.UserRepository;
import com.vincent.esspringboot.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RestHighLevelClient restHighLevelClient; // 假设你已经在Spring Boot中配置了RestHighLevelClient


    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> selectAll() {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.select("*");
        return userMapper.selectList(wrapper);
    }



    @Override
    public Page<UserEntity> getUsersSortedByAgeAsc(int page, int size) {
        // 创建一个带有排序信息的Pageable对象，这里按照age升序排序
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "age"));
        return userRepository.findAll(pageable); // 使用findAll方法并传入Pageable对象
    }
}
