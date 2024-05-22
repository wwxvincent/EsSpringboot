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
import org.elasticsearch.index.query.*;
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

    // 基本查询：查询匹配name字段的数据
    // 如果singer字段也匹配，会将singer权重乘2，如果无匹配，不影响基本查询结果
    @Override
    public SearchResponse searchSongs2(String name, String singer) throws IOException {
        // 基本查询
        MatchQueryBuilder nameQuery = QueryBuilders.matchQuery("name", name);
        // 设置singer字段，作为filter条件
        MatchQueryBuilder singerFilter = QueryBuilders.matchQuery("singer", singer);

        // Function Score查询
        //这里创建了一个函数评分过滤器，它将"singerFilter"作为过滤条件，并定义了一个评分函数，
        // 即ScoreFunctionBuilders.weightFactorFunction(2f)。这个函数表示如果"singerFilter"匹配成功，
        // 就将文档的评分乘以2。
        FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[]{
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(singerFilter, ScoreFunctionBuilders.weightFactorFunction(2f))
        };
        //创建一个函数评分查询对象 functionScoreQuery。
        // 在创建时，它使用了之前定义的过滤器数组 filterFunctionBuilders 和主查询条件 nameQuery。
        //其中"nameQuery"是我们用来匹配"name"字段的查询条件，
        // 而"filterFunctionBuilders"则是我们定义的过滤器数组，用来根据"singer"字段的匹配情况进行评分调整。
        //.scoreMode(FunctionScoreQuery.ScoreMode.MULTIPLY):
        // 这一行设置了函数评分查询的评分模式为乘法模式，即将各个函数评分相乘得到最终评分。
        FunctionScoreQueryBuilder functionScoreQuery = QueryBuilders.functionScoreQuery(nameQuery, filterFunctionBuilders)
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

    @Override
    public SearchResponse searchSongs3(String name, String singer) throws IOException {
        // 创建 Match 查询
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", name);

        // 创建 Term 查询
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("singer", singer);

        // 创建 Boosting 查询
        BoostingQueryBuilder boostingQuery = QueryBuilders.boostingQuery(matchQueryBuilder, termQueryBuilder)
                .negativeBoost(0.5f);

        // 创建 SearchSourceBuilder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boostingQuery);

        // 创建 SearchRequest
        SearchRequest searchRequest = new SearchRequest("songs"); // 替换为你的索引名称
        searchRequest.source(searchSourceBuilder);

        // 执行查询
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        return searchResponse;
    }


}
