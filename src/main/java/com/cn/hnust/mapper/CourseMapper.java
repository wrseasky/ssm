package com.cn.hnust.mapper;

import java.util.List;

import com.cn.hnust.pojo.Course;

public interface CourseMapper {
	
	List<Course> selectByName(String name);
	
	void insertAllByXml(List<Course> courses);
	
	List<Course> selectCoursesByIds(List<Integer> ids);
	
}