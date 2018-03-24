package com.cn.hnust.service;

import com.cn.hnust.pojo.Student;  

public interface StudentService {  
	Student selectByPrimaryKey(Integer id);
	
    int deleteStudentById(Integer id);

    int insert(Student record, Integer courseId);

    int updateByPrimaryKeySelective(Student student);
}  
