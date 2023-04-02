package org.example.client;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/2
 */
public class ESTest_Client {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
        System.out.println(esClient);
        esClient.close();
    }
}
