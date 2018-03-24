package com.cn.hnust.es;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Transient;

import com.cn.hnust.pojo.User;

@SuppressWarnings("all")
public class ObjAnalysis {
	/**
	 * obj中属性为null时,返回null
	 * 
	 * @param obj
	 * @return
	 */
	public static Map convertObjToMap(Object obj) {
		if (obj == null) {
			return null;
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		int length = fields.length;
		Map<String, Object> reMap = new HashMap<String, Object>(length);
		try {
			for (int i = 0; i < length; i++) {
				try {
					Field f = fields[i];
					Transient annotation = f.getAnnotation(Transient.class);
					if (annotation == null) {
						f.setAccessible(true);
						Object o = f.get(obj);
						String name = fields[i].getName();
						reMap.put(name, o);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return reMap;
	}
}