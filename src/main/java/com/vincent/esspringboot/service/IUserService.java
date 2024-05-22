package com.vincent.esspringboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vincent.esspringboot.entity.UserEntity;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IUserService extends IService<UserEntity> {
    List<UserEntity> selectAll();

    Page<UserEntity> getUsersSortedByAgeAsc(int page, int size);
}
