package com.cn.hnust.es.advanced;

import org.elasticsearch.search.sort.SortOrder;

public class T {
	public static void main(String[] args) {
		//ESSort s = new ESSort();
		//s.setSortOrder(SortOrder.ASC);
		MechanismSearchService mss = new MechanismSearchService();
		mss.init();
	}
}