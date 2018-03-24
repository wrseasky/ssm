package com.cn.hnust.mapper;

import java.util.List;

import com.cn.hnust.pojo.WechatAccess;
import com.cn.hnust.pojo.WechatAccessParam;

@SuppressWarnings("all")
public interface WechatAccessMapper {

	public List<WechatAccess> getWechatAccessInfo(WechatAccessParam wechatAccessParam);
}
