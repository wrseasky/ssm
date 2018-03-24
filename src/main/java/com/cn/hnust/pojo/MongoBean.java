package com.cn.hnust.pojo;

import java.util.List;

public class MongoBean {
	
	private String database;
	private String table;
	private List<Object> detail;

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public List<Object> getDetail() {
		return detail;
	}

	public void setDetail(List<Object> detail) {
		this.detail = detail;
	}
}
