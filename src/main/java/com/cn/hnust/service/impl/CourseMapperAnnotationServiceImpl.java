package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.mapper.extend.CourseMapperAnnotation;
import com.cn.hnust.pojo.Course;
import com.cn.hnust.service.CourseMapperAnnotationService;

@Service
public class CourseMapperAnnotationServiceImpl implements CourseMapperAnnotationService {
	@Autowired
	private CourseMapperAnnotation courseMapperAnnotation;

	@Override
	public int add(Course course) {
		return courseMapperAnnotation.add(course);
	}

	@Override
	public int deleteById(int id) {
		return courseMapperAnnotation.deleteById(id);
	}

	@Override
	public int update(Course course) {
		return courseMapperAnnotation.update(course);
	}

	@Override
	public Course getCourseById(int id) {
		return courseMapperAnnotation.getCourseById(id);
	}

	@Override
	public List<Course> getAllCourses() {
		return courseMapperAnnotation.getAllCourses();
	}

	@Override
	public void insertAllByAnnotation(List<Course> courses) {
		courseMapperAnnotation.insertAllByAnnotation(courses);
	}

	@Override
	public void insertAllByXml(List<Course> courses) {
		courseMapperAnnotation.insertAllByXml(courses);
	}

	@Override
	public List<Course> selectCoursesByIds(List<Integer> ids) {
		return courseMapperAnnotation.selectCoursesByIds(ids);
	}

}
