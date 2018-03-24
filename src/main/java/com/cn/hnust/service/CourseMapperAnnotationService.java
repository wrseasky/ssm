package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.Course;

public interface CourseMapperAnnotationService {
	
	public int add(Course course);

	public int deleteById(int id);

	public int update(Course course);

	public Course getCourseById(int id);

	public List<Course> getAllCourses();
	
	void insertAllByAnnotation(List<Course> courses);
	
	void insertAllByXml(List<Course> courses);
	
	public List<Course> selectCoursesByIds(List<Integer> ids);
}
