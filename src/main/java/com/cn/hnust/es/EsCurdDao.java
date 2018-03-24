package com.cn.hnust.es;

import java.util.Date;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
public class EsCurdDao {
	
	//@Autowired
	private TransportClient transportClient;
	
	public String update(InformationFlow fromJson, String id, String type, String index) {
		Map map = ObjAnalysis.convertObjToMap(fromJson);
		map.put("@timestamp", new Date());
		UpdateRequest updateRequest = new UpdateRequest(index, type, id);
		updateRequest.doc(map);
		UpdateResponse actionGet = transportClient.update(updateRequest).actionGet();
		return actionGet.getId();
	}
	
	public String save(InformationFlow fromJson, String id, String type, String index){
		Map map = ObjAnalysis.convertObjToMap(fromJson);
		map.put("@timestamp", new Date());
		IndexResponse actionGet = transportClient.prepareIndex(index, type, id)
				.setSource(map)
				.execute()
				.actionGet();
		return actionGet.getId();
	}
	
	

	public String delete(String id, String type, String index) {
		
		 DeleteResponse actionGet = transportClient.prepareDelete(index, type, id)
				.execute()
				.actionGet();
		 
		return actionGet.getId();
	}
	
	public Map get(String id, String type, String index) {
		GetResponse actionGet = transportClient.prepareGet(index, type, id)
			.execute()
			.actionGet();
		
		Map<String, Object> sourceAsMap = actionGet.getSourceAsMap();
		
		return sourceAsMap;
		
	}
	
	public String updateMatrix(Map map, String id, String type, String index) {
		map.put("@timestamp", new Date());
		map.remove("_id");
		map.remove("_index");
		map.remove("_type");
		UpdateRequest updateRequest = new UpdateRequest(index, type, id);
		updateRequest.doc(map);
		UpdateResponse actionGet = transportClient.update(updateRequest)
				.actionGet();
		return actionGet.getId();
	}
	

}
