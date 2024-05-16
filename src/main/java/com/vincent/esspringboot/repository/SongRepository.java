package com.vincent.esspringboot.repository;

import com.vincent.esspringboot.entity.SongEntity;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends ElasticsearchRepository<SongEntity, String> {

    List<SongEntity> findByName(String name);

    List<SongEntity> findBySinger(String keyword);

//    List<SongEntity> findByNameFuzzy(String keyword);
}
