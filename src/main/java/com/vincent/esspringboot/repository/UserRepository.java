package com.vincent.esspringboot.repository;

import com.vincent.esspringboot.entity.UserEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<UserEntity, String> {
}
