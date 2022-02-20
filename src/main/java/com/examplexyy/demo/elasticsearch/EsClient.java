package com.examplexyy.demo.elasticsearch;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.*;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collections;

/**
 * @author: xiongyayun
 * @date: 2022/2/19 11:13
 */
public class EsClient {

    public static RestHighLevelClient getClient () {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("106.52.223.163", 9200, "http")
                )
        );

        //region setting
        return client;
    }


    public static void createIndex() throws IOException {

        RestHighLevelClient client = getClient();
        CreateIndexRequest request = new CreateIndexRequest("productxyy");

        //region setting
        request.settings(Settings.builder()
                .put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 0)
        );

        //region 别名
        request.alias(new Alias("product_alias").filter(QueryBuilders.termQuery("name", "xiaomi")));
        //endregion
        request.timeout(TimeValue.timeValueMillis(2));
        //endregion


        // 同步
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        // 异步
        client.indices().createAsync(request, RequestOptions.DEFAULT, new ActionListener<CreateIndexResponse>() {
            @Override
            public void onResponse(CreateIndexResponse createIndexResponse) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });

        // 是否所有节点都已确认请求
        createIndexResponse.isAcknowledged();
        // 在超时之前是否为索引中的每个碎片启动所需数量的碎片副本
        createIndexResponse.isShardsAcknowledged();
        client.close();
    }


    public static void getIndex() throws IOException {
        RestHighLevelClient client = getClient();

        GetIndexRequest request = new GetIndexRequest("product*");
        GetIndexResponse response = client.indices().get(request, RequestOptions.DEFAULT);
        String[] indices = response.getIndices();
        for (String indexName : indices) {
            System.out.println("index name:" + indexName);
        }

        client.close();
    }

    public static void insertData() throws IOException {
        //region 创建连接
        RestHighLevelClient client = getClient();
        //endregion

        //region 准备数据
        //endregion

        //region 创建Request对象
        //插入数据，index不存在则自动根据匹配到的template创建。index没必要每天创建一个，如果是为了灵活管理，最低建议每月一个 yyyyMM。
        IndexRequest request = new IndexRequest("productxyy");
        //endregion

        //region 组装数据
        Product product = new Product();
        product.setDate(new Timestamp(System.currentTimeMillis()));
        product.setId(1);
        product.setName("xyyde name ");
        Gson gson = new Gson();
        //最好不要自定义id 会影响插入速度。
        request.id(product.getId().toString());
        request.source(gson.toJson(product)
                , XContentType.JSON);
        //endregion

        //region 执行Index操作
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        //endregion

        System.out.println(response);
        client.close();
    }

    public static void main(String[] args) throws IOException {
//        createIndex();
        getIndex();
//        insertData();
//        batchInsertData();
        delIndex();

    }

    @SneakyThrows
    public static void batchInsertData() {
        //region 创建连接
        RestHighLevelClient client = getClient();
        //endregion

        //region 创建Request对象
        //批量插入数据，更新和删除同理
        BulkRequest request = new BulkRequest("productxyy");
        //endregion

        //region 组装数据
        Gson gson = new Gson();
        Product product = new Product();
        product.setPrice(3999.00);
        product.setDesc("xioami");
        for (int i = 2; i < 12; i++) {
            product.setName("name" + i);
            request.add(new IndexRequest()
                    .id(Integer.toString(i))
                    .source(gson.toJson(product)
                            , XContentType.JSON)
            );
        }
        //endregion

        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);

        System.out.println("数量:" + response.getItems().length);
        client.close();
    }

    public void getById() throws IOException {
        //region 创建连接
        RestHighLevelClient client = getClient();
        //endregion

        //region 创建Request对象
        //注意 这里查询使用的是别名。
        GetRequest request = new GetRequest("test_index", "6");
        //endregion

        //region 组装数据
        String[] includes = {"name", "price"};
        String[] excludes = {"desc"};
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
        //只查询特定字段。如果需要查询所有字段则不设置该项。
        request.fetchSourceContext(fetchSourceContext);
        //endregion

        //region 响应数据
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        //endregion

        System.out.println(response);
        client.close();

    }

    @SneakyThrows
    public static void delIndex() {
        RestHighLevelClient client = getClient();
        DeleteIndexRequest request = new DeleteIndexRequest("test_index");
        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
        if (response.isAcknowledged()) {
            System.out.println("删除index成功!");
        } else {
            System.out.println("删除index失败!");
        }
        client.close();
    }

    public static void delById() throws IOException {
        //region Description
        RestHighLevelClient client = getClient();
        //endregion

        DeleteRequest request = new DeleteRequest("test_index", "1");

        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);

        System.out.println(response);
        client.close();
    }

    public void multiGetById() throws IOException {
        //region Description
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
        //endregion

        //region Description
        //根据多个id查询
        MultiGetRequest request = new MultiGetRequest();
        //endregion

        //region Description
        request.add("test_index", "6");
        //两种写法
        request.add(new MultiGetRequest.Item(
                "test_index",
                "7"));
        //endregion

        //region Description
        MultiGetResponse response = client.mget(request, RequestOptions.DEFAULT);
        //endregion
        for (MultiGetItemResponse itemResponse : response) {
            System.out.println(itemResponse.getResponse().getSourceAsString());
        }
        client.close();
    }

    public void updateByQuery() throws IOException {
        //region 连接
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
        //endregion

        //region 请求对象
        UpdateByQueryRequest request = new UpdateByQueryRequest("test_index");
        //endregion

        //region 组装数据
        //默认情况下，版本冲突会中止 UpdateByQueryRequest 进程，但是你可以用以下命令来代替
        //设置版本冲突继续
//        request.setConflicts("proceed");
        //设置更新条件
        request.setQuery(QueryBuilders.termQuery("name", "name2"));
//        //限制更新条数
//        request.setMaxDocs(10);
        request.setScript(
                new Script(ScriptType.INLINE, "painless", "ctx._source.desc+='#';", Collections.emptyMap()));
        //endregion

        BulkByScrollResponse response = client.updateByQuery(request, RequestOptions.DEFAULT);

        System.out.println(response);
        client.close();
    }
}
