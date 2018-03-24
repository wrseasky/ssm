package com.cn.hnust.mapper;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.Student;

public interface StudentMapper {
	
	Student selectByPrimaryKey(Integer id);
	
	int deleteByMidKey(Integer id);
	
    int deleteByPrimaryKey(Integer id);

    int insertStudent(Student record);

	int insertStudentMid(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);

	int updateByPrimaryKeySelective(Student record);
}