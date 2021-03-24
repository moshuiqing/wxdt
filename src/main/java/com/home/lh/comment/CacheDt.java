package com.home.lh.comment;

import java.util.List;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.home.lh.system.entity.Answer;
import com.home.lh.system.entity.Questions;
import com.home.lh.system.service.IAnswerService;
import com.home.lh.system.service.IQuestionsService;
import com.home.lh.util.Global;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ai996
 *  自定义初始化加载  （在所有配置类加载完成之后加载）
 */
@Component
@Order(3)
@Slf4j
public class CacheDt implements CommandLineRunner {
	
	

	
	@Autowired
	private IQuestionsService questionsService;
	

	@Autowired
	private IAnswerService answerService;
	
	@Autowired
	private EhCacheManager ehCacheManager;

	@Override
	public void run(String... args) throws Exception {
		//WeiXinUtil.setWeixinInfo();
		try {
			//单选
			
			List<Questions> questions= questionsService.list(new QueryWrapper<Questions>().eq("type","1").eq("isdel", "0"));//单选题
			for (Questions q : questions) {
				List<Answer> answers= answerService.list(new QueryWrapper<Answer>().eq("tmid",q.getId()).orderByAsc("bs"));
				q.setAnswers(answers);
			}
			List<Questions> questions2= questionsService.list(new QueryWrapper<Questions>().eq("type","2").eq("isdel", "0"));//单选题
			for (Questions q : questions2) {
				List<Answer> answers= answerService.list(new QueryWrapper<Answer>().eq("tmid",q.getId()).orderByAsc("bs"));
				q.setAnswers(answers);
			}
			// 存入缓存
			Cache<String, Object> sysCache = ehCacheManager.getCache("tmhc");

			sysCache.put(Global.DXTMHC, questions);
			sysCache.put(Global.ALLTMHC, questions2);
			
			
			log.info("缓存");
		} catch (Exception e) {
			log.info("缓存失败");
			e.printStackTrace();
		}
		
	}

}
