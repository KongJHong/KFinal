package com.hong.common.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hong.common.domain.ElasticEntity;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author : KongJHong
 * @Date : 2020-09-29 10:49
 * @Version : 1.0
 * Description     : es服务封装
 *
 * includeDefaults: 返回是否包含默认信息
 */
@Component
@PropertySource("classpath:es-config.properties")
@Slf4j
public class BaseElasticService {


    private final  RestHighLevelClient restHighLevelClient;

    private final ObjectMapper objectMapper;

    // 分片数目
    private static final int NUMBER_OF_SHARDS = 3;

    // 备份数目
    private static final int NUMBER_OF_REPLICAS = 0;

    @Value("${es.properties}")
    private List<String> properties;

    /**
     * TODO 预加载 索引
     */
    @PostConstruct
    private void preLoading() {

    }

    public BaseElasticService( RestHighLevelClient restHighLevelClient,
                               ObjectMapper objectMapper) {
        this.restHighLevelClient = restHighLevelClient;
        this.objectMapper = objectMapper;
    }

    /**
     *  制定配置项的判断索引是否存在，注意与 isExistsIndex 区别
     * @param idxName index名
     * @return boolean
     */
    public boolean indexExist(String idxName) throws IOException {
        GetIndexRequest request = new GetIndexRequest(idxName);
        //TRUE-返回本地信息检索状态，FALSE-还是从主节点检索状态
        request.local(false);
        //是否适应被人可读的格式返回
        request.humanReadable(true);
        //是否为每个索引返回所有默认设置
        request.includeDefaults(false);
        //控制如何解决不可用的索引以及如何扩展通配符表达式,忽略不可用索引的索引选项，仅将通配符扩展为开放索引，并且不允许从通配符表达式解析任何索引
        request.indicesOptions(IndicesOptions.LENIENT_EXPAND_OPEN);
        return restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
    }

    /**
     * 断某个index是否存在
     * @param idxName 索引名
     * @return 是否存在
     * @throws Exception 连接异常
     */
    public boolean isExistsIndex(String idxName) throws Exception {
        return restHighLevelClient.indices().exists(new GetIndexRequest(idxName),RequestOptions.DEFAULT);
    }

    /**
     * 新增索引
     * @param idxName   索引名字
     * @param idxSQL    索引描述
     */
    public void createIndex(String idxName, String idxSQL) {
        try {
            if (this.indexExist(idxName)) {
                log.error("idxName={} 已经存在，idxSql={}", idxName, idxSQL);
                return;
            }

            CreateIndexRequest request = new CreateIndexRequest(idxName);
            buildSetting(request);
            request.mapping(idxSQL, XContentType.JSON);
            CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
            if (!response.isAcknowledged()) {
                log.error("创建索引失败, idxName={}", idxName);
            }

        }catch(IOException ex) {
            log.error("ES连接失败");
        }
    }

    /**
     * 删除索引
     * @param idxName 索引名
     */
    public void deleteIndex(String idxName) {
        try {
            if (!indexExist(idxName)) {
                log.warn(" idxName={} 不存在", idxName);
                return;
            }
            restHighLevelClient.indices().delete(new DeleteIndexRequest(idxName), RequestOptions.DEFAULT);
        }catch (IOException ex) {
            log.error("删除索引失败, 索引: {}", idxName);
        }
    }

    /**
     * 搜索索引下元素，由外部控制搜索条件
     * @param idxName   索引名
     * @param builder   搜索条件
     * @param c         返回类型
     * @return          搜索到的数组对象,错误时返回空
     */
    public <T> List<T> search(String idxName, SearchSourceBuilder builder, Class<T> c) {
        SearchRequest request = new SearchRequest(idxName);
        request.source(builder);
        try{
            SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();
            List<T> result = new ArrayList<>(hits.length);
            for (SearchHit hit: hits) {
                result.add(objectMapper.readValue(hit.getSourceAsString(), c));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("search时ES或json读取出错, 搜索语句{}", builder.toString());
            return new ArrayList<T>();
        }
    }

    /**
     * 删除一行数据
     * @param idxName 索引
     * @param entity    删除目标(id)
     */
    public void deleteOne(String idxName, ElasticEntity entity) {
        DeleteRequest request = new DeleteRequest(idxName);
        request.id(entity.getId());
        try {
            restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除doc错误, index: {}, id: {}", idxName, entity.getId());
        }
    }

    /**
     * 增加一行数据
     * @param idxName 索引名
     * @param entity    存储对象
     */
    public void insertOne(String idxName, ElasticEntity entity) {
        IndexRequest request = new IndexRequest(idxName);
        request.id(entity.getId());
        try {
            request.source(objectMapper.writeValueAsString(entity.getData()), XContentType.JSON);
            restHighLevelClient.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("ES添加行错误, index: {}, id: {}", idxName, entity.getId());
        }
    }

    /**
     * 更新一条数据
     * @param idxName 索引名
     * @param entity    目标数据
     */
    public void updateOne(String idxName, ElasticEntity entity) {

        UpdateRequest request = new UpdateRequest(idxName, entity.getId());
        try {
            request.doc(objectMapper.writeValueAsString(entity.getData()), XContentType.JSON);
            restHighLevelClient.update(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("ES添加行错误, index: {}, id: {}", idxName, entity.getId());
        }
    }

    /**
     * 批量插入
     * @param idxName index
     * @param entities 插入数据
     */
    public void batchInsert(String idxName, List<ElasticEntity> entities) {
        BulkRequest request = new BulkRequest();
        entities.forEach(item -> {
            try {
                request.add(new IndexRequest(idxName).id(item.getId())
                .source(objectMapper.writeValueAsString(item.getData()), XContentType.JSON));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                log.error("json转换错误, id: {}", item.getId());
            }
        });

        try {
            restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            entities.forEach(item -> System.out.print(item.getId()));
            log.error("ES 批量插入失败, index: {}", idxName);
        }
    }


    /**
     * 批量删除
     * @param idxName index
     * @param idList  id list
     */
    public <T> void batchDelete(String idxName, Collection<T> idList) {
        BulkRequest request = new BulkRequest();
        idList.forEach(item -> request.add(new DeleteRequest(idxName, item.toString())));

        try {
            restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            idList.forEach(item-> System.out.print(item.toString() + ' '));
            log.error("ES 批量删除错误, index: {}", idxName);
        }
    }


    /**
     * 设置分片
     */
    private void buildSetting(CreateIndexRequest request) {
        request.settings(Settings.builder().put("index.number_of_shards", NUMBER_OF_SHARDS)
                            .put("index.number_of_replicas", NUMBER_OF_REPLICAS));
    }
}
