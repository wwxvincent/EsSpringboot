package com.vincent.esspringboot.service.impl;

import com.vincent.esspringboot.service.IIndexService;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 5/24/24
 * @Description:
 */
@Service
public class IndexServiceImpl implements IIndexService {

    @Autowired
    private RestHighLevelClient restHighLevelClient; // 假设你已经在Spring Boot中配置了RestHighLevelClient

    @Override
    public boolean createIndexWithSynonyms(String path) throws IOException {

        // Load synonyms from classpath
        ClassPathResource resource = new ClassPathResource(path);
        String synonyms;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            synonyms = reader.lines().collect(Collectors.joining("\n"));
        }
        // Create index request
        CreateIndexRequest request = new CreateIndexRequest("file_synonyms_index");

        // Set analyzer
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.startObject("settings");
            {
                builder.startObject("analysis");
                {
                    builder.startObject("filter");
                    {
                        builder.startObject("my_synonym");
                        {
                            builder.field("type", "synonym_graph"); //把synonym 换成 synonym_graph 试一试
//                            builder.field("synonyms_path", path); // 确保path指向同义词文件
//                            这里不是es启动容器类的文件， 这是本地文件，所以换成下面这句话
                            builder.field("synonyms", synonyms.split("\n")); // 直接嵌入同义词内容
                            builder.field("ignore_case", true);
                            builder.field("expand", true); // 对于IK分词器，通常设置expand为true以展开多词同义词
                        }
                        builder.endObject();
                    }
                    builder.endObject();
                    builder.startObject("analyzer");
                    {
                        builder.startObject("my_custom_analyzer");
                        {
                            // 假设您已经安装了ik分词器插件
                            builder.field("type", "custom");
                            builder.field("tokenizer", "ik_max_word");
                            builder.array("filter",  "my_synonym","lowercase");
                        }
                        builder.endObject();
                    }
                    builder.endObject();
                }
                builder.endObject();
            }
            builder.endObject();

            builder.startObject("mappings");
            {
                builder.startObject("properties");
                {
                    builder.startObject("name"); // 替换为您的字段名
                    {
                        builder.field("type", "text");
                        builder.field("analyzer", "my_custom_analyzer");
                        builder.field("search_analyzer", "my_custom_analyzer"); // 可选，指定搜索时使用的分析器
                    }
                    builder.endObject();
                }
                builder.endObject();
            }
            builder.endObject();
        }
        builder.endObject();

        // 将XContentBuilder的内容设置到CreateIndexRequest中
        request.source(builder);

        // 在这里执行CreateIndexRequest
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);

        // 返回是否成功
        return createIndexResponse.isAcknowledged();
    }


}
