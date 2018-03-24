package com.cn.hnust.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.cn.hnust.mapper.UserMapper;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.UserService;  
  
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:/spring-mybatis.xml"})
public class TestMyBatis {  
    private static Logger logger = Logger.getLogger(TestMyBatis.class);  
//  private ApplicationContext ac = null;  
    @Autowired 
    private UserService userService = null;  
  
    @Autowired
    private UserMapper userMapper;
//  @Before  
//  public void before() {  
//      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
//      userService = (IUserService) ac.getBean("userService");  
//  }  
  
    @Test  
    public void test1() {  
        User user = userService.getUserById(1);  
        System.out.println(user.getUserName());  
        // logger.info("值："+user.getUserName());
        logger.info(JSON.toJSONString(user));
        int id = 0;
        userMapper.deleteByPrimaryKey(id);
        userMapper.selectByPrimaryKey(id);
        userMapper.insert(new User());
        userMapper.updateByPrimaryKey(new User());
        
        userMapper.insertSelective(new User());//user中属性为空时不插入
        userMapper.updateByPrimaryKeySelective(new User());//user中属性为空时不更新
    }  
}  
