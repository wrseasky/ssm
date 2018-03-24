package com.cn.hnust.mongodbController;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.hnust.pojo.MongoBean;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

@Controller
@SuppressWarnings("all")
public class MongoController {
	@Autowired
	private MongoTemplate mongoTem;

	/**
	 * mongodb中collection ==> mysql中表
	 */

	@RequestMapping("/insertmongo")
	public void insert() {
		BasicDBObject document = new BasicDBObject();
		document.put("database", "mkyongDB");
		document.put("table", "hosting");

		BasicDBObject documentDetail = new BasicDBObject();
		documentDetail.put("records", 1);
		documentDetail.put("index", "vps_index1");
		documentDetail.put("active", "true");

		document.put("detail", documentDetail);

		mongoTem.insert(document, "aa"); // 文档 collection即理解为表
	}

	// 插入多个
	@RequestMapping("/insertmany")
	public void insertCollections() {

		BasicDBObject document1 = new BasicDBObject();
		document1.put("database", 8);
		document1.put("table", "c");
		document1.put("active", "true");
		
		BasicDBObject document2 = new BasicDBObject();
		document2.put("database", 8);
		document2.put("table", "a");
		document2.put("active", "true");
		
		BasicDBObject document3 = new BasicDBObject();
		document3.put("database", 5);
		document3.put("table", "b");

		List<BasicDBObject> list = new ArrayList<>();
		list.add(document1);
		list.add(document2);
		list.add(document3);

		mongoTem.insert(list, "aa");
	}

	@RequestMapping("/insertlist")
	public void insertlist() {
		BasicDBObject document = new BasicDBObject();
		document.put("database", "mkyongDB");
		document.put("table", "hosting");

		List<BasicDBObject> list = new ArrayList<>();
		BasicDBObject num1 = new BasicDBObject();
		num1.put("num1", "1");
		num1.put("num11", "11");
		list.add(num1);

		BasicDBObject num2 = new BasicDBObject();
		num2.put("num2", "2");
		num2.put("num22", "22");
		list.add(num2);

		BasicDBObject num3 = new BasicDBObject();
		num3.put("num3", "3");
		num3.put("num33", "33");
		list.add(num3);

		document.put("nums", list);
		mongoTem.insert(document, "aa");
	}

	@RequestMapping("/insertmongot")
	public void inserttwo() {
		BasicDBObjectBuilder documentBuilder = BasicDBObjectBuilder.start().add("database", "mkyongDB").add("table", "hosting");

		BasicDBObjectBuilder documentBuilderDetail = BasicDBObjectBuilder.start().add("records", "99").add("index", "vps_index1").add("active", "true");

		documentBuilder.add("detail", documentBuilderDetail.get());

		mongoTem.insert(documentBuilder.get(), "aa");
	}

	@RequestMapping("/insertmongoth")
	public void insertth() {
		Map<String, Object> documentMap = new HashMap<String, Object>();
		documentMap.put("database", "mkyongDB");
		documentMap.put("table", "hosting");

		Map<String, Object> documentMapDetail = new HashMap<String, Object>();
		documentMapDetail.put("records", "99");
		documentMapDetail.put("index", "vps_index1");
		documentMapDetail.put("active", "true");

		documentMap.put("detail", documentMapDetail);

		mongoTem.insert(new BasicDBObject(documentMap), "aa");
	}

	@RequestMapping("/insertmongof")
	public void insertf() {
		String json = "{'database':'dd','table':'hosting','detail':{'records':99,'index':'vps_index1','active':true}}";
		DBObject dbObject = (DBObject) JSON.parse(json);
		mongoTem.insert(dbObject, "aa");
	}

	@RequestMapping("/selectmongo")
	public void select() {
		// 设置查询的条件
		DBObject dbObject = new BasicDBObject();
		dbObject.put("database", "dd");

		// 设置显示的字段
		// 注意: 只设置false的话,其他全为true, 设置有true的话,只显示true的字段
		DBObject fieldObject = new BasicDBObject();
		fieldObject.put("_id", true);
		fieldObject.put("database", true);
		fieldObject.put("table", true);
		fieldObject.put("detail", true);

		Query query = new BasicQuery(dbObject, fieldObject);
		List<Map> find = mongoTem.find(query, Map.class, "aa");
		for (Map map : find) {
			System.out.println(map);
		}
	}

	@RequestMapping("/selectmongojuhe")
	public void selectjuhe() {
		DBCollection collection = mongoTem.getCollection("aa");
		/* 相等times="" */
		DBObject matchBasicDBObjet = new BasicDBObject("$match", new BasicDBObject("table", "hosting"));
		/* Group操作 */
		DBObject groupFields = new BasicDBObject("_id", new BasicDBObject("database", "$database").append("table", "$table"));//以database table分组其中_id是固定的
		//查询出的结果: "_id" : { "database" : "mkk" , "table" : "hosting"}
		groupFields.put("count", new BasicDBObject("$sum", 1));
		DBObject group = new BasicDBObject("$group", groupFields);

		// 排序操作 -1降续 1 升续
		//DBObject sortchBasicDBObjet = new BasicDBObject("$sort", new BasicDBObject("count", -1));
		DBObject limitBasicDBObjet = new BasicDBObject("$limit", 10);
		// project 操作 即为显示的字段0不显示1显示 	显示的字段是groupFields中 _id 下面的字段  	
		DBObject projectBasicDBObjet = new BasicDBObject("$project", new BasicDBObject("_id", 0).append("database", "$_id.database").append("table", "$_id.table").append("count", 1));
		//"_id" : { "database" : "mkk" , "table" : "hosting"}中_id不显示,_id中的database和table显示
		List<DBObject> list = new ArrayList<DBObject>();
		list.add(matchBasicDBObjet);
		list.add(group);
		//list.add(sortchBasicDBObjet);
		list.add(limitBasicDBObjet);
		list.add(projectBasicDBObjet);

		AggregationOutput output = collection.aggregate(list);
		CommandResult result = output.getCommandResult();
		Object object = result.get("result");
		System.out.println(object);
	}

	// 单个字段的or
	@RequestMapping("/selector")
	public void selector() {
		DBObject queryCondition = new BasicDBObject();
		// age<=5 OR age>=8
		BasicDBList values = new BasicDBList();
		values.add(new BasicDBObject("database", new BasicDBObject("$gte", 8)));
		values.add(new BasicDBObject("database", new BasicDBObject("$lte", 5)));
		queryCondition.put("$or", values);

		DBCursor find = mongoTem.getCollection("aa").find(queryCondition);
		for (DBObject dbObject : find) {
			Integer database = (Integer) dbObject.get("database");
			String table = (String) dbObject.get("table");
			System.out.println("database : " + database + "	table : " + table);
		}
	}

	// 多个字段的or
	@RequestMapping("/selectorm")
	public void selectorm() {
		DBObject queryCondition = new BasicDBObject();
		// active=zz OR database>=8
		BasicDBList values = new BasicDBList();
		values.add(new BasicDBObject("active", "zz"));
		values.add(new BasicDBObject("database", new BasicDBObject("$gte", 8)));
		queryCondition.put("$or", values);

		DBCursor find = mongoTem.getCollection("aa").find(queryCondition);
	}

	// 单个字段的in
	@RequestMapping("/selectin")
	public void selectin() {
		DBObject queryCondition = new BasicDBObject();
		// age in [13, 47]
		BasicDBList values = new BasicDBList();
		values.add(13);
		values.add(47);
		queryCondition.put("age", new BasicDBObject("$in", values));

		DBCursor dbCursor = mongoTem.getCollection("aa").find(queryCondition);
	}

	// 分页 排序查询
	@RequestMapping("/selectmongoorder")
	public void getActiveFansComments() throws Exception {

		DBObject dbObject = new BasicDBObject();
		dbObject.put("database", "dd");

		DBObject fieldObject = new BasicDBObject();
		fieldObject.put("database", true);
		fieldObject.put("table", true);
		fieldObject.put("detail", true);
		fieldObject.put("_id", false);

		Query query = new BasicQuery(dbObject, fieldObject);

		// 单个字段排序
		// Sort sort = new Sort(Direction.DESC, "detail.records");
		// 多个字段排序
		Order o = new Order(Direction.DESC, "detail.records");
		Order o1 = new Order(Direction.DESC, "detail.active");
		List<Order> list = new ArrayList<Sort.Order>();
		list.add(o);
		list.add(o1);
		Sort sort = new Sort(list);

		query.skip(1).limit(10).with(sort); // skip跳过的记录条数
		List<Map> find = mongoTem.find(query, Map.class, "aa");
		System.out.println();
	}

	//模糊查询
	@RequestMapping("/selectmmhu")
	public void selectmmhu() {

		// 完全匹配
		// Pattern pattern = Pattern.compile("^name$",Pattern.CASE_INSENSITIVE);
		// 右匹配
		// Pattern pattern =
		// Pattern.compile("^.*name$",Pattern.CASE_INSENSITIVE);
		// 左匹配
		// Pattern pattern =
		// Pattern.compile("^name.*$",Pattern.CASE_INSENSITIVE);
		// 模糊匹配
		Pattern pattern = Pattern.compile("^.*dda.*$", Pattern.CASE_INSENSITIVE);
		// 查询条件
		BasicDBObject query = new BasicDBObject();
		query.put("database", pattern);

		// 排序条件 1表示升续； －1表示降续
		BasicDBObject sort = new BasicDBObject();
		sort.put("detail.records", 1);

		DBCollection collection = mongoTem.getCollection("aa");

		DBCursor cur = collection.find(query).sort(sort);

		int count = 0;
		while (cur.hasNext()) {
			DBObject obj = cur.next();
			System.out.println("database=" + obj.get("database") + ",table=" + obj.get("table") + ",detail=" + obj.get("detail"));
			count++;
		}
	}

	// 查询实体类
	@RequestMapping("/selectbean")
	public void selectToObject() {
		List<MongoBean> find = mongoTem.find(Query.query(Criteria.where("database").is("dd").and("detail.records").is(98)), MongoBean.class, "aa");
		System.out.println();
	}

	
	@RequestMapping("/selectmohu")
	public void selectmohu() {
		// ☆☆☆☆☆☆☆☆ query的查询要有实体类对象 ☆☆☆☆☆☆☆☆☆☆☆//
		Query query = new Query();
		query.addCriteria(Criteria.where("database").regex(".*?dda.*"));
		// regex(".*?" + name + ".*");
		// .匹配\n之外的任何字符,*匹配前面的字符0次或者多次,?匹配前面的字符0次或者1次
		long count = mongoTem.count(query, "aa");
		System.out.println(count);
	}

	// 更新多个字段
	@RequestMapping("/updatemongo")
	private void updateProcessLandLog() {
		List<String> fields = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		fields.add("database");
		fields.add("table");

		values.add("database");
		values.add("table");

		Update update = new Update();
		int size = fields.size();
		for (int i = 0; i < size; i++) {
			String field = fields.get(i);
			Object value = values.get(i);
			update.set(field, value);
		}

		Query query = new Query(Criteria.where("database").is("db"));
		// 更新database是db的一条记录
		// WriteResult updateFirst = mongoTem.updateFirst(query, update, "aa");
		// 更新database是db的所有记录
		mongoTem.updateMulti(query, update, "aa");

	}

	// 删除集合aa中table为b的记录
	@RequestMapping("/deletemongo")
	public void remove() {
		WriteResult remove = mongoTem.remove(new Query(Criteria.where("table").is("b")), "aa");
	}

}
