package com.cn.hnust.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.hnust.mapper.ClassesMapper;
import com.cn.hnust.pojo.Classes;
import com.cn.hnust.service.ClassesService;

@Service
@Transactional
public class ClassesServiceImpl implements ClassesService {
	@Autowired
	private ClassesMapper classesMapper;

	@Override
	public Classes selectByPrimaryKey(Integer id) {
		return classesMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return classesMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Classes record) {
		return classesMapper.insert(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Classes record) {
		return classesMapper.updateByPrimaryKeySelective(record);
	}
}
