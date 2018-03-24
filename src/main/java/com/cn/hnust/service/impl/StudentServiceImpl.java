package com.cn.hnust.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.mapper.StudentMapper;
import com.cn.hnust.pojo.Student;
import com.cn.hnust.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentMapper studentMapper;

	@Override
	public Student selectByPrimaryKey(Integer id) {
		return studentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteStudentById(Integer id) {
		studentMapper.deleteByMidKey(id);
		return studentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Student record, Integer courseId) {
		studentMapper.insertStudent(record);
		return studentMapper.insertStudentMid(record.getSid(), courseId);
	}

	@Override
	public int updateByPrimaryKeySelective(Student record) {
		return studentMapper.updateByPrimaryKeySelective(record);
	}
}
