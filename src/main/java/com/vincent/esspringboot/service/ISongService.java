package com.vincent.esspringboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vincent.esspringboot.entity.SongEntity;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.io.IOException;
import java.util.List;

public interface ISongService extends IService<SongEntity> {
    List<SongEntity> selectAll();

    public SearchResponse searchSongs(String query) throws IOException;

    SearchResponse searchSongs2(String name, String singer) throws IOException;

    SearchResponse searchSongs3(String name, String singer) throws IOException;

}
