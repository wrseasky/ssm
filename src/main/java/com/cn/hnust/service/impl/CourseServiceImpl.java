package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.mapper.CourseMapper;
import com.cn.hnust.pojo.Course;
import com.cn.hnust.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseMapper courseMapper;

	@Override
	public List<Course> selectByName(String name) {
		return courseMapper.selectByName(name);
	}

}
