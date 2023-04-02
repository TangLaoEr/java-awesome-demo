package org.example.doc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.example.utils.ClientUtils;

import java.io.IOException;

/**
 * 文档操作
 *
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/2
 */
public class Doc_Option {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = ClientUtils.getInstance();
        batchAdd(esClient);
        try {
            esClient.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 新增文档
     *
     * @param client
     * @throws IOException
     */
    public static void addDoc(RestHighLevelClient client) throws IOException {
        // 新增文档 - 请求对象
        IndexRequest request = new IndexRequest();
        // 设置索引及唯一性标识
        request.index("user").id("1001");
        // 创建数据对象
        User user = new User();
        user.setName("zhangsan");
        user.setAge(30);
        user.setSex("男");
        // 使用jackson
        ObjectMapper objectMapper = new ObjectMapper();
        // 转换为json字符串
        String productJson = objectMapper.writeValueAsString(user);
        // 添加文档数据，数据格式为 JSON 格式
        request.source(productJson, XContentType.JSON);
        // 客户端发送请求，获取响应对象
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        // 3.打印结果信息
        System.out.println("_index:" + response.getIndex());
        System.out.println("_id:" + response.getId());
        System.out.println("_result:" + response.getResult());
    }

    /**
     * 修改文档
     *
     * @param client
     * @throws IOException
     */
    public static void updateDoc(RestHighLevelClient client) throws IOException {
        UpdateRequest request = new UpdateRequest();
        request.index("user").id("1001");
        request.doc(XContentType.JSON, "sex", "女");

        // 客户端发送请求，获取响应对象
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        System.out.println("_index:" + response.getIndex());
        System.out.println("_id:" + response.getId());
        System.out.println("_result:" + response.getResult());
    }

    /**
     * 查询文档
     *
     * @param client
     * @throws IOException
     */
    public static void selectDoc(RestHighLevelClient client) throws IOException {
        GetRequest request = new GetRequest().index("user").id("1001");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        System.out.println("_index:" + response.getIndex());
        System.out.println("_type:" + response.getType());
        System.out.println("_id:" + response.getId());
        System.out.println("source:" + response.getSourceAsString());
    }

    /**
     * 删除文档
     *
     * @param client
     * @throws IOException
     */
    public static void deleteDoc(RestHighLevelClient client) throws IOException {
        DeleteRequest request = new DeleteRequest().index("user").id("1");
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(response.toString());
    }

    /**
     * 批量新增
     */
    public static void batchAdd(RestHighLevelClient client) throws IOException {
        // 创建批量新增请求对象
        BulkRequest request = new BulkRequest();
        request.add(new
                IndexRequest().index("user").id("1001").source(XContentType.JSON, "name",
                "zhangsan"));
        request.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON, "name",
                "lisi"));
        request.add(new
                IndexRequest().index("user").id("1003").source(XContentType.JSON, "name",
                "wangwu"));
        // 客户端发送请求，获取响应对象
        BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
        // 打印结果信息
        System.out.println("took:" + responses.getTook());
        for (BulkItemResponse item : responses.getItems()) {
            System.out.println(item);
        }
        System.out.println("items:" + responses.getItems());
    }

    /**
     * 批量删除
     * @param client
     * @throws IOException
     */
    public static void batchDelete(RestHighLevelClient client) throws IOException {
        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest().index("user").id("1001"));
        request.add(new DeleteRequest().index("user").id("1002"));
        request.add(new DeleteRequest().index("user").id("1003"));
        BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println("took:" + responses.getTook());
        System.out.println("items:" + responses.getItems());
        for (BulkItemResponse item : responses.getItems()) {
            System.out.println("item:" + item);
        }
    }
}
