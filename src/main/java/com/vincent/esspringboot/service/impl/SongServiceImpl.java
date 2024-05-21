package com.vincent.esspringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vincent.esspringboot.entity.SongEntity;
import com.vincent.esspringboot.mapper.SongMapper;
import com.vincent.esspringboot.service.ISongService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class SongServiceImpl extends ServiceImpl<SongMapper, SongEntity> implements ISongService {

    @Autowired
    private SongMapper songMapper;

    @Autowired
    private RestHighLevelClient restHighLevelClient; // 假设你已经在Spring Boot中配置了RestHighLevelClient




    @Override
    public List<SongEntity> selectAll() {
        QueryWrapper<SongEntity> wrapper = new QueryWrapper<>();
        wrapper.select("*");
        return songMapper.selectList(wrapper);
    }

    @Override
    public SearchResponse searchSongs(String query) throws IOException {
        // 构建多字段匹配查询

        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(query, "name", "singer", "note")
                .field("singer", 2f); // 设置singer字段的boost为2
        // 创建SearchSourceBuilder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(multiMatchQueryBuilder);

        // 创建SearchRequest
        SearchRequest searchRequest = new SearchRequest("songs"); // 指定索引名
        searchRequest.source(searchSourceBuilder);

        // 执行查询
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        return searchResponse;
    }

    @Override
    public SearchResponse searchSongs2(String name, String singer) throws IOException {
        MatchQueryBuilder nameQuery = QueryBuilders.matchQuery("name", name);
        MatchQueryBuilder singerFilter = QueryBuilders.matchQuery("singer", singer);

        // Function Score查询
        FunctionScoreQueryBuilder.FilterFunctionBuilder filterFunctionBuilder = new FunctionScoreQueryBuilder.FilterFunctionBuilder(singerFilter, ScoreFunctionBuilders.weightFactorFunction(2f));

        FunctionScoreQueryBuilder functionScoreQuery = QueryBuilders.functionScoreQuery(nameQuery, filterFunctionBuilder.getScoreFunction())
                .scoreMode(FunctionScoreQuery.ScoreMode.MULTIPLY);


        // 构建SearchSourceBuilder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(functionScoreQuery);

        // 构建SearchRequest
        SearchRequest searchRequest = new SearchRequest("songs"); // 索引名为songs
        searchRequest.source(searchSourceBuilder);

        // 执行查询
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        return searchResponse;

    }





}
