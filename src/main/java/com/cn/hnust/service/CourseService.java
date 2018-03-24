package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.Course;

public interface CourseService {
	
	List<Course> selectByName(String name);
}
