package com.vincent.esspringboot;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.NodesResponse;
import org.elasticsearch.client.RestClient;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ESTest {



    //生成es连接
//    private ElasticsearchClient getEsClient() {
//        try {
//            //调用es有同步和异步之分，下列方法是同步阻塞调用
//            // Create the low-level client
//            RestClient restClient = RestClient.builder(
//                    new HttpHost("192.168.2.130", 9200)).build();
//
//            // Create the transport with a Jackson mapper
//            ElasticsearchTransport transport = new RestClientTransport(
//                    restClient, new JacksonJsonpMapper());
//
//            // And create the API client
//            ElasticsearchClient client = new ElasticsearchClient(transport);
//
//            return client;
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("生成esClient失败" + e);
//        }
//        return null;
//    }
//
//    //查询es系统相关信息
//    @Test
//    public void testCatRequest() throws IOException {
//        ElasticsearchClient client = this.getEsClient();
//
//        // Cat requests should have the "format=json" added by the transport
//        NodesResponse nodes = client.cat().nodes(_0 -> _0);
//        System.out.println(ModelTestCase.toJson(nodes, client._transport().jsonpMapper()));
//    }


}
