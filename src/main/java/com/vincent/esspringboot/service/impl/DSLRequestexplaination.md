elasticsearch 中的执行语句
```sql 
GET /songs/_search
{
  "query": {
    "function_score": {
      "query": { "match": { "name": "雨" }},
      "functions": [
        {
          "filter": { "match": { "singer": "许" }},
          "weight": 2
        }
      ],
      "score_mode": "multiply"
    }
  }
}
```
对应的java代码
```java
  public SearchResponse searchSongs2(String name, String singer) throws IOException {
        MatchQueryBuilder nameQuery = QueryBuilders.matchQuery("name", name);
        MatchQueryBuilder singerFilter = QueryBuilders.matchQuery("singer", singer);


        // Function Score查询
        FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[]{
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(singerFilter, ScoreFunctionBuilders.weightFactorFunction(2f))
        };
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
```