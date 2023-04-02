package org.example.index;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.example.utils.ClientUtils;

import java.io.IOException;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/2
 */
public class Index_Option {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = ClientUtils.getInstance();
        // createIndex(esClient);
        // getIndex(esClient);
        deleteIndex(esClient);
        try {
            esClient.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建索引
     */
    public static void createIndex(RestHighLevelClient esClient) {
        CreateIndexRequest request = new CreateIndexRequest("user1");
        CreateIndexResponse response = null;
        try {
            response = esClient.indices().create(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boolean acknowledged = response.isAcknowledged();

        System.out.println("操作状态 = " + acknowledged);

    }

    /**
     * 查看索引
     */
    public static void getIndex(RestHighLevelClient esClient) throws IOException {
        // 查询索引 - 请求对象
        GetIndexRequest request = new GetIndexRequest("user");
        // 发送请求，获取响应
        GetIndexResponse response = esClient.indices().get(request,
                RequestOptions.DEFAULT);
        System.out.println("aliases:" + response.getAliases());
        System.out.println("mappings:" + response.getMappings());
        System.out.println("settings:" + response.getSettings());
    }

    /**
     * 删除索引
     */
    public static void deleteIndex(RestHighLevelClient client) throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("user");
        AcknowledgedResponse response = client.indices().delete(request,
                RequestOptions.DEFAULT);
        System.out.println("操作结果 ： " + response.isAcknowledged());
    }

}
