package com.cn.hnust.mapper;

import com.cn.hnust.pojo.Classes;

public interface ClassesMapper {
	
	Classes selectByPrimaryKey(Integer id);
	
    int deleteByPrimaryKey(Integer id);

    int insert(Classes record);

    int updateByPrimaryKeySelective(Classes record);
}