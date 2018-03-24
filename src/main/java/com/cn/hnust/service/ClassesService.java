package com.cn.hnust.service;

import com.cn.hnust.pojo.Classes;  

public interface ClassesService {  
	Classes selectByPrimaryKey(Integer id);
	
    int deleteByPrimaryKey(Integer id);

    int insert(Classes record);

    int updateByPrimaryKeySelective(Classes student);
}  
