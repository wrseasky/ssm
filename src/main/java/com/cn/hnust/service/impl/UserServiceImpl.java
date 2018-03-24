package com.cn.hnust.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.mapper.UserMapper;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.UserService;  
  
@Service 
public class UserServiceImpl implements UserService {  
    @Autowired   
    private UserMapper userMapper;

	public User getUserById(int userId){  
        return this.userMapper.selectByPrimaryKey(userId);  
    }

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(User record) {
		return userMapper.updateByPrimaryKey(record);
	} 
}  