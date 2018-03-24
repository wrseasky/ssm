package com.cn.hnust.service.dubbo;

public class CustomerServiceImpl implements CustomerService{  
    @Override  
    public String getName() {  
        System.out.print("我打印");  
        return "打印结果";  
    }  
} 
