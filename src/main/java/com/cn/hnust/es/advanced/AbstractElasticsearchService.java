package com.cn.hnust.es.advanced;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

public abstract class AbstractElasticsearchService {

	private static Logger logger = Logger.getLogger(AbstractElasticsearchService.class);

	private static Client client;

	/**
	 * 初始化方法
	 */
	public abstract void init();

	/**
	 * 获取client
	 * 
	 * @return
	 * @throws Exception
	 */
	public Client getClient() throws Exception {

		// if (client == null) {
		// client = TransportClient.builder().build().addTransportAddress(new
		// InetSocketTransportAddress(InetAddress.getByName("localhost"),
		// 9400));
		// }
		client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(IP), PORT));
		return client;
	}

	public static final String CLUSTER_NAME = "elasticsearch"; // 实例名称
	private static final String IP = "127.0.0.1";
	private static final int PORT = 9400; // 端口
	// 1.设置集群名称：默认是elasticsearch，并设置client.transport.sniff为true，使客户端嗅探整个集群状态，把集群中的其他机器IP加入到客户端中
	// 对ES2.0有效
	private static Settings settings = Settings.settingsBuilder().put("cluster.name", CLUSTER_NAME).put("client.transport.sniff", false).build();

	static {
		try {
			client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(IP), PORT));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭client
	 */
	public void closeClient() {
		client.close();
	}

	/**
	 * 创建索引
	 * 
	 * @throws Exception
	 */
	public void createIndex(String indexName) throws Exception {
		CreateIndexResponse createIndexResponse = getClient().admin().indices().prepareCreate(indexName).execute().actionGet();
		System.out.println(createIndexResponse.isAcknowledged());
	}

	/**
	 * 创建domain对应的映射
	 *
	 * @param analyzeFields
	 * @param indexType
	 * @return
	 * @throws Exception
	 */

	// TODO do not know what is indexType???????????????????

	public XContentBuilder getDomainMapping(List<String> analyzeFields, String indexType) throws Exception {

		XContentBuilder mapping = XContentFactory.jsonBuilder().startObject().startObject(indexType).startObject("properties");

		for (String field : analyzeFields) {
			mapping = mapping.startObject(field).field("type", "string").field("store", "yes").endObject();
		}
		mapping = mapping.endObject().endObject().endObject();
		return mapping;
	}

	/**
	 * 创建映射
	 * 
	 * @param mapping
	 * @param indexName
	 * @param indexType
	 * @return
	 * @throws Exception
	 */
	public PutMappingResponse createMapping(XContentBuilder mapping, String indexName, String indexType) throws Exception {

		PutMappingRequest mappingRequest = Requests.putMappingRequest(indexName).type(indexType).source(mapping);
		PutMappingResponse mappingResponse = getClient().admin().indices().putMapping(mappingRequest).actionGet();
		return mappingResponse;
	}

	/**
	 * 封装esTerms的请求参数
	 * 
	 * @param esTerms
	 * @return
	 */
	protected String buildQueryString(List<ESTerm> esTerms) {
		StringBuilder stringBuilder = new StringBuilder();
		if (esTerms == null) {
			logger.debug("The esTerm is empty");
			return null;
		}

		for (int i = 0; i < esTerms.size(); i++) {
			ESTerm esTerm = esTerms.get(i);

			if (i == 0) {
				stringBuilder.append(esTerm.getName() + ":" + esTerm.getValue());
				continue;
			}
			stringBuilder.append("OR");
			stringBuilder.append(esTerm.getName() + ":" + esTerm.getValue());
		}
		return stringBuilder.toString();
	}

	/**
	 * 删除一个索引库，相当于删除数据库
	 * 
	 * @param indexName
	 * @return
	 */
	public boolean deleteIndex(String indexName) {

		if (indexName == null) {
			logger.debug("The indexName is empty");
			return false;
		}
		try {
			DeleteIndexRequest request = new DeleteIndexRequest(indexName);
			DeleteIndexResponse deleteIndexResponse = getClient().admin().indices().delete(request).actionGet();
			if (!deleteIndexResponse.isAcknowledged()) {
				logger.debug("The delete fail");
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * @param keyword
	 *            需要查询的关键字
	 * @param esMatches
	 *            构建查询条件
	 */
	public void buildESMatch(String keyword, List<ESTerm> esMatches) {

		if (keyword != null) {
			keyword = keyword.trim();
		}
		List<String> useToSearch = new ArrayList<String>();// 此处可以在spring中进行配置
		if (useToSearch != null) {
			final String finalTerm = keyword;
			for (String searchName : useToSearch) {
				ESTerm esMatch = new ESTerm();
				esMatch.setName(searchName);
				esMatch.setValue(finalTerm);
				esMatches.add(esMatch);
			}
		}
	}
}
