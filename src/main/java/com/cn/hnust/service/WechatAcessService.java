package com.cn.hnust.service;

import java.util.Map;

import com.cn.hnust.pojo.Paging;
import com.cn.hnust.pojo.WechatAccess;
import com.cn.hnust.pojo.WechatAccessParam;
@SuppressWarnings("all")
public interface WechatAcessService {
	public Paging<WechatAccess> getWechatAccess(WechatAccessParam wechatAccessParam);
	public Map getWechatMenu();
}
