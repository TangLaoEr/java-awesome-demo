package org.example.utils;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/2
 */
public class ClientUtils {

    public static RestHighLevelClient getInstance() {
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
        // System.out.println(esClient);
        // esClient.close();
        return esClient;
    }


}
