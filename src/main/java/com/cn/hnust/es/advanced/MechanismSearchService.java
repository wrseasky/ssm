package com.cn.hnust.es.advanced;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;

import com.alibaba.fastjson.JSONObject;

public class MechanismSearchService extends AbstractElasticsearchService {

	private static Logger logger = Logger.getLogger(AbstractElasticsearchService.class);

	@Override
	public void init() {
		try {
			String indexName = "dragon";
			String indexType = "mechanism";
			createIndex(indexName, true);

			List<String> fields = new ArrayList<String>();
			fields.add("id");
			fields.add("accountType");
			fields.add("organizationName");
			fields.add("accountCode");
			fields.add("organizationName");
			fields.add("accountCode");
			fields.add("createDate");
			fields.add("contacts");
			fields.add("callPhone");
			fields.add("capitalAccountLimits");
			fields.add("subAccountLimits");
			fields.add("status");
			fields.add("username");
			fields.add("password");
			fields.add("freezeNote");
			fields.add("accountNote");
			
			//TODO do not know what is mechanism ??????????    
			
			XContentBuilder mapping = getDomainMapping(fields, indexType);
			createMapping(mapping, indexName, indexType);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 创建机构列表的索引
	 * 
	 * @param mechanisms
	 * @throws Exception
	 */
	public void createMechismIndex(List<Mechanism> mechanisms) throws Exception {

		if (mechanisms == null) {
			return;
		}
		for (Mechanism mechanism : mechanisms) {
			String jsonData = JSONObject.toJSONString(mechanism);
			IndexResponse indexResponse = getClient().prepareIndex().setIndex("dragon").setType("mechanism").setId(mechanism.getId().toString()).setSource(jsonData).execute().actionGet();
			System.out.println("创建索引的版本号:" + indexResponse.getVersion());
		}
	}

	/**
	 * 增加一条索引
	 * 
	 * @param mechanism
	 */
	public void insertMechismIndex(Mechanism mechanism) {
		if (mechanism == null) {
			logger.debug("The mechanism is empty");
			return;
		}
		try {
			String jsonData = JSONObject.toJSONString(mechanism);
			IndexResponse indexResponse = getClient().prepareIndex().setIndex("dragon").setType("mechanism").setId(mechanism.getId().toString()).setSource(jsonData).execute().actionGet();
			logger.debug("create index version is:" + indexResponse.getVersion());
		} catch (Exception e) {
			logger.debug("The insert Mechism is fail");
			logger.debug(e.getMessage());
		}
	}

	/**
	 * 修改一条索引的内容
	 * 
	 * @param mechanism
	 */
	public void updateMecjismIndex(Mechanism mechanism) {
		if (mechanism == null) {
			logger.debug("The mechanism is empty");
			return;
		}
		try {
			String jsonData = JSONObject.toJSONString(mechanism);
			UpdateResponse updateResponse = getClient().prepareUpdate().setIndex("dragon").setType("mechanism").setId(mechanism.getId().toString()).setDoc(jsonData.getBytes()).execute().actionGet();
			logger.debug("update idnex version is:" + updateResponse.getVersion());
		} catch (Exception e) {
			logger.debug("The update mechanism is empty");
			logger.debug(e.getMessage());
		}
	}

	/**
	 * 搜索列表的结果
	 * 
	 * @param esTerm
	 * @return
	 * @throws Exception
	 */
	public SearchResponse findMechismIndex(ESTerm esTerm, List<ESTerm> esMatches, ESRange esRange, List<ESSort> esSortList, ESAggregation esAggregation) throws Exception {

		SearchResponse response = null;
		try {
			SearchRequestBuilder searchRequestBuilder = buildSearchRequestBuilder();
			BoolQueryBuilder qb = QueryBuilders.boolQuery();
			searchRequestBuilder.setQuery(qb);

			if (esTerm != null) {
				qb.must(QueryBuilders.termQuery(esTerm.getName(), esTerm.getValue()));
			}
			buildQueryBuilder(esMatches, esRange, esSortList, esAggregation, searchRequestBuilder, qb);
			logger.debug(searchRequestBuilder.toString());
			response = searchRequestBuilder.execute().actionGet();
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	private SearchRequestBuilder buildSearchRequestBuilder() throws Exception {
		return getClient().prepareSearch("dragon").setTypes("mechanism").setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
	}

	/**
	 * 
	 * @param esTerms
	 * @param esMatches
	 * @param esRange
	 * @param esSortList
	 * @param esAggregation
	 * @return
	 * @throws Exception
	 */
	public SearchResponse findMechismIndex(List<ESTerm> esTerms, List<ESTerm> esMatches, ESRange esRange, List<ESSort> esSortList, ESAggregation esAggregation) throws Exception {
		SearchResponse response = null;
		try {
			SearchRequestBuilder searchRequestBuilder = buildSearchRequestBuilder();
			BoolQueryBuilder qb = QueryBuilders.boolQuery();
			searchRequestBuilder.setQuery(qb);

			if (esTerms != null) {
				for (ESTerm term : esTerms) {
					qb.should(QueryBuilders.termQuery(term.getName(), term.getValue()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * 封装请求的参数
	 * 
	 * @param esMatches
	 * @param esRange
	 * @param esSortList
	 * @param esAggregation
	 * @param searchRequestBuilder
	 * @param qb
	 * @return
	 */
	public void buildQueryBuilder(List<ESTerm> esMatches, ESRange esRange, List<ESSort> esSortList, ESAggregation esAggregation, SearchRequestBuilder searchRequestBuilder, BoolQueryBuilder qb) {

		String queryString = null;

		queryString = buildQueryString(esMatches);

		// 封装esTerm的条件
		if (queryString != null) {
			qb.must(QueryBuilders.queryStringQuery(queryString));
		}
		// 封装排序条件
		if (esSortList != null) {
			for (ESSort esSort : esSortList) {
				FieldSortBuilder sortBuilder = new FieldSortBuilder(esSort.getPropertyName());
				sortBuilder.order(esSort.getSortOrder());
				sortBuilder.unmappedType("long");
				searchRequestBuilder.addSort(sortBuilder);
			}
		}
		// 封装聚合条件
		if (esAggregation != null) {
			searchRequestBuilder.addAggregation(AggregationBuilders.terms(esAggregation.getAggregationName()).field(esAggregation.getPropertyName()));
		}
		// 封装区间条件，价格
		if (esRange != null) {
			qb.filter(QueryBuilders.rangeQuery(esRange.getPropertyName()).from(esRange.getFrom()).to(esRange.getTo()));
		}

	}

	/**
	 * 删除一个type下具体的某一个索引数据
	 * 
	 * @param id
	 * @param type
	 */
	public void deleteIndex(String id, String type) {

		try {
			DeleteResponse deleteResponse = getClient().prepareDelete().setIndex("dragon").setType(type).setId(id).execute().actionGet();
			Set<String> results = deleteResponse.getHeaders();
			for (String m : results) {
				logger.debug(m);
			}

			if (deleteResponse.isFound()) {
				logger.debug("the delete id is :" + id + "and type is:" + type);
				return;
			}
			logger.debug("Could not found to delete with id is :" + id + "and type is :" + type);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试删除一个type
	 * 
	 * @param type
	 */
	public void deleteType(String type) {

		try {
			DeleteResponse deleteResponse = getClient().prepareDelete().setIndex("dragon").setType(type).execute().actionGet();
			if (deleteResponse.isFound()) {
				logger.debug("the delete  type is:" + type);
				return;
			}
			logger.debug("Could not found to  type is:" + type);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}

	}

	/**
	 * 创建索引，是否需要覆盖原索引
	 * 
	 * @param indexName
	 *            索引库的名字
	 * @param overried
	 *            是否需要覆盖
	 */
	public void createIndex(String indexName, Boolean overried) {
		try {
			boolean exits = getClient().admin().indices().prepareExists(indexName).execute().actionGet().isExists();
			if (exits && !overried) {
				logger.debug("The index " + indexName + "isExits,and not need overried");
				return;
			}
			if (exits) {
				DeleteIndexRequest request = new DeleteIndexRequest(indexName);
				DeleteIndexResponse deleteIndexResponse = getClient().admin().indices().delete(request).actionGet();
				if (!deleteIndexResponse.isAcknowledged()) {
					logger.error("Fail to delete the index " + indexName);
				}
			}
			getClient().admin().indices().prepareCreate(indexName).execute().actionGet();
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
	}

}
