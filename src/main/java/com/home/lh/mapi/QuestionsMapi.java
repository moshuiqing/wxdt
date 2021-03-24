package com.home.lh.mapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.lh.system.entity.Questions;
import com.home.lh.util.Global;
import com.home.lh.util.SimpleUtil;
import com.home.lh.util.po.ReturnMap;

@RestController
@RequestMapping("/mapi/questions/")
public class QuestionsMapi {

	@Autowired
	private EhCacheManager ehCacheManager;

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("getQues")
	public ReturnMap getQues() {
		ReturnMap rm =new ReturnMap();
		// 存入缓存
		Cache<String, Object> sysCache = ehCacheManager.getCache("tmhc");
		List<Questions> questions= (List<Questions>) sysCache.get(Global.DXTMHC);//获取全部单选
		List<Questions> questions2 = (List<Questions>) sysCache.get(Global.ALLTMHC);//获取全部多选
		SimpleUtil<Questions> util = new SimpleUtil<>();
		List<Questions> dx= util.randomTopic(questions, 17);
		List<Questions> dx2= util.randomTopic(questions2, 3);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dx", dx);
		map.put("dx2", dx2);
		rm.setCode(1);
		rm.setMsg("获取成功");
		rm.setData(map);
		return rm;
	}

}
