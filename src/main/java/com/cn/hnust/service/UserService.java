package com.cn.hnust.service;

import com.cn.hnust.pojo.User;  

public interface UserService {  
    public User getUserById(int userId);
    
    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}  
