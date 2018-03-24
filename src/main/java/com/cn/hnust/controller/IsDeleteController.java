package com.cn.hnust.controller;

import java.util.List;

import com.cn.hnust.mapper.IsDeleteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cn.hnust.pojo.IsDelete;

@RestController
@RequestMapping("/del")
public class IsDeleteController {
	@Autowired
	private IsDeleteMapper isDeleteMapper;

	@RequestMapping("/show")
	public List<IsDelete> toIndex() {
		List<IsDelete> selectAll = isDeleteMapper.selectAll();
		return selectAll;
	}

	@RequestMapping(value = "/showu", produces = "application/json;charset=utf-8")
	public String showu() {

		return "中文";
	}

}
