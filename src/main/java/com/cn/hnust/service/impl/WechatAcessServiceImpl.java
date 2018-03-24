package com.cn.hnust.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cn.hnust.mapper.WechatAccessMapper;
import com.cn.hnust.mapper.WechatMenuMapper;
import com.cn.hnust.pojo.Paging;
import com.cn.hnust.pojo.WeChatMenu;
import com.cn.hnust.pojo.WechatAccess;
import com.cn.hnust.pojo.WechatAccessParam;
import com.cn.hnust.service.WechatAcessService;

@Service
@SuppressWarnings("all")
public class WechatAcessServiceImpl implements WechatAcessService {
	@Autowired
	private WechatMenuMapper wechatMenuMapper;
	@Autowired
	private WechatAccessMapper wechatAccessMapper;

	@Override
	public Paging<WechatAccess> getWechatAccess(WechatAccessParam wechatAccessParam) {
		Paging<WechatAccess> page = new Paging<>();
		List<WechatAccess> wechatAccessInfos = wechatAccessMapper.getWechatAccessInfo(wechatAccessParam);
		page.setRows(wechatAccessInfos);
		return page;
	}
	

	@Override
	public Map getWechatMenu() {
		WeChatMenu weChatMenu = wechatMenuMapper.selectByPrimaryKey(1l);
		String value = weChatMenu.getValue();
		Map map = (Map) JSONObject.parse(value);
		List list = (List) map.get("button");
		Map results = new HashMap<>();
		for (Object object : list) {
			Map m = (Map) object;
			Object obj = m.get("sub_button");
			if (obj != null) {
				List l = (List) obj;
				for (Object obje : l) {
					Map mm = (Map) obje;
					results.put((mm.get("url") == null ? mm.get("key") : mm.get("url")), mm.get("name"));
				}
			} else {
				Map result = new HashMap<Object, Object>();
				results.put(m.get("url"), m.get("name"));
			}
		}
		return results;
	}
}
