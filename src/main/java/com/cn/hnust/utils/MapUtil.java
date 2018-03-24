package com.cn.hnust.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapUtil {

	public static Map<String, String> getSimpleParams(Map<String, String[]> parameterMap) {
		Map<String, String> map = new HashMap<>(parameterMap.size());
		Set<Entry<String,String[]>> entrySet = parameterMap.entrySet();
		for (Entry<String, String[]> entry : entrySet) {
			map.put(entry.getKey(), entry.getValue()[0]);
		}
		return map;
	}
}
