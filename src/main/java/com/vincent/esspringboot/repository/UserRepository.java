package com.vincent.esspringboot.repository;

import com.vincent.esspringboot.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<UserEntity, String> {

    @Query("{\"bool\": {\"match_all\": {}}}")
    Page<UserEntity> findAllByAgeAsc(Pageable pageable);
}
