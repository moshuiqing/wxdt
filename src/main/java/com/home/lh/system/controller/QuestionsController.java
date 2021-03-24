package com.home.lh.system.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.home.lh.system.entity.Answer;
import com.home.lh.system.entity.Questions;
import com.home.lh.system.service.IAnswerService;
import com.home.lh.system.service.IQuestionsService;
import com.home.lh.util.po.LayuiPage;
import com.home.lh.util.po.LayuiResult;
import com.home.lh.util.po.ReturnMap;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 刘浩
 * @since 2021-02-08
 */
@RestController
@RequestMapping("/questions/")
public class QuestionsController {

	@Autowired
	private IQuestionsService questionsService;

	@Autowired
	private IAnswerService answerService;

	/**
	 * 分页查询
	 * 
	 * @param p
	 * @param answer
	 * @return
	 */
	@RequestMapping("pageFound")
	public LayuiResult pageFound(LayuiPage p, Questions questions) {
		LayuiResult result = new LayuiResult();
		Page<Questions> page = new Page<>(p.getPage(), p.getLimit());
		QueryWrapper<Questions> queryWrapper =new QueryWrapper<Questions>();
		queryWrapper.setEntity(questions);
		queryWrapper.orderByAsc("createtime");
		
		IPage<Questions> iPage = questionsService.page(page, queryWrapper);
		result.setCode(0);
		result.setCount(iPage.getTotal());

		List<Questions> list = iPage.getRecords();
		for (Questions q : list) {
			List<Answer> answers = answerService.list(new QueryWrapper<Answer>().eq("tmid", q.getId()));
			q.setAnswers(answers);
		}

		result.setData(list);
		result.setMsg("获取成功");
		return result;
	}

	/**
	 * 编辑
	 * 
	 * @param questions
	 * @return
	 */
	@RequestMapping("editQuestions")
	public ReturnMap editQuestions(Questions questions) {
		ReturnMap rm = new ReturnMap();
		Boolean flag;
		
		if(questions.getDa().trim().length()==1) {
			//单选
			questions.setType("1");			
		}else {
			//多选
			questions.setType("2");			
		}
	
		if (StringUtils.isEmpty(questions.getId())) {
			// 新增	
			questions.setCreatetime(new Date());
			String id = UUID.randomUUID().toString();
			questions.setId(id);
			flag = questionsService.save(questions);
			List<Answer> answers = JSONArray.parseArray(questions.getJsonAs(), Answer.class);
			for (Answer a : answers) {
				a.setTmid(id);
				String aid = UUID.randomUUID().toString();
				a.setId(aid);
			}
			flag = answerService.saveBatch(answers);

		} else {
			// 修改
			flag = questionsService.updateById(questions);

			answerService.remove(new QueryWrapper<Answer>().eq("tmid", questions.getId()));

			List<Answer> answers = JSONArray.parseArray(questions.getJsonAs(), Answer.class);
			for (Answer a : answers) {
				a.setTmid(questions.getId());
				String aid = UUID.randomUUID().toString();
				a.setId(aid);
			}
			flag = answerService.saveBatch(answers);
		}

		if (flag) {
			rm.setCode(1);
			rm.setMsg("成功");
		}

		return rm;
	}

	/**
	 * @param questions
	 * @return
	 */
	@RequestMapping("delQuestions")
	public ReturnMap delQuestions(String id) {

		ReturnMap rm = new ReturnMap();
		if(StringUtils.isEmpty(id)) {
			rm.setCode(-1);
			rm.setMsg("参数异常");
		}else {
			boolean flag =  questionsService.removeById(id);
			if(flag) {
				rm.setCode(1);
				rm.setMsg("删除成功");
			}else {
				rm.setCode(-1);
				rm.setMsg("删除失败");				
			}
		}
		return rm;
	}

}
