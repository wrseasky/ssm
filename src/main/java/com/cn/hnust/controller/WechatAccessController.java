package com.cn.hnust.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.pojo.Paging;
import com.cn.hnust.pojo.WechatAccess;
import com.cn.hnust.pojo.WechatAccessParam;
import com.cn.hnust.service.WechatAcessService;

@Controller
public class WechatAccessController {
@Autowired
private WechatAcessService wechatAcessService;
//	@RequestMapping("/wechatAccess")
//	@ResponseBody
//	public Paging<WechatAccess> getWechatAccess(HttpServletRequest request) {
//		Map<String, String[]> parameterMap = request.getParameterMap();
//		Map<String, String> simpleParams = MapUtil.getSimpleParams(parameterMap);
//		Paging<WechatAccess> wechatAccess = wechatAcessService.getWechatAccess(simpleParams);
//		return wechatAccess;
//	}
	@RequestMapping("/wechatAccess")
	@ResponseBody
	public Paging<WechatAccess> getWechatAccess(WechatAccessParam wechatAccessParam) {
		Paging<WechatAccess> wechatAccess = wechatAcessService.getWechatAccess(wechatAccessParam);
		return wechatAccess;
	}
}
