package com.cn.hnust.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.hnust.pojo.User;
import com.cn.hnust.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;

	@RequestMapping("/showUser")
	public String toIndex(HttpServletRequest request, Model model) {
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		return "showUser";
	}

	@RequestMapping("/{dir}/{page}")
	public void defaultPages(@PathVariable String dir, @PathVariable String page) {
		System.out.println(dir + page);
	}
	
	@RequestMapping("/dir/{dir}/page/{page}")
	public void restful(@PathVariable String dir, @PathVariable String page) {
		System.out.println(dir + page);
	}
	
	@RequestMapping("/update")
	public void update() {
		User record1 = userService.getUserById(1);
		User record2 = userService.getUserById(1);
		
		//userService.updateByPrimaryKeySelective(record);
		
		//打印当前商品信息  
	    System.out.println(record1);  
	    System.out.println(record2);  
	      
	    //更新商品信息1  
	    record1.setUserName("测试1");
		int updateResult1 = userService.updateByPrimaryKeySelective(record1);
	    System.out.println("修改商品信息1"+(updateResult1==1?"成功":"失败"));  
	      
	    //更新商品信息2  
	    record2.setUserName("测试2");
	    int updateResult2 = userService.updateByPrimaryKeySelective(record1);
	    System.out.println("修改商品信息2"+(updateResult2==1?"成功":"失败")); 
	}
}
