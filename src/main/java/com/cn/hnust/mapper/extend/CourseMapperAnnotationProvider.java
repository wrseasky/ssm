package com.cn.hnust.mapper.extend;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import com.cn.hnust.pojo.Course;

@SuppressWarnings("all")
public class CourseMapperAnnotationProvider {

	public String insertAll(Map map) {
		List<Course> users = (List<Course>) map.get("list");
		StringBuilder sb = new StringBuilder();
		sb.append("insert into jpa.course ");
		sb.append("(id, course_name)");
		sb.append("values ");
		MessageFormat mf = new MessageFormat("(null, #'{'list[{0}].courseName})");
		for (int i = 0; i < users.size(); i++) {
			sb.append(mf.format(new Object[] { i }));
			if (i < users.size() - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}
}
