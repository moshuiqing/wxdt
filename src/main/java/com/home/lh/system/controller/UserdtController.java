package com.home.lh.system.controller;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.home.lh.system.entity.Userdt;
import com.home.lh.system.service.IUserdtService;
import com.home.lh.util.po.LayuiPage;
import com.home.lh.util.po.LayuiResult;
import com.home.lh.util.po.ReturnMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 刘浩
 * @since 2021-02-08
 */
@RestController
@RequestMapping("/userdt/")
public class UserdtController {
	
	
	@Autowired
	private IUserdtService userdtService;
	
	/**
	 * @param userdt
	 * @param p
	 * @return
	 */
	@RequestMapping("pageFound")
	public LayuiResult pageFound(Userdt userdt,LayuiPage p) {
		LayuiResult result = new LayuiResult();
		Page<Userdt> page = new Page<>(p.getPage(), p.getLimit());
		QueryWrapper<Userdt> queryWrapper = new QueryWrapper<>();
		queryWrapper.like(!StringUtils.isEmpty(userdt.getUserName()),"userName", userdt.getUserName());
		queryWrapper.like(!StringUtils.isEmpty(userdt.getPhone()),"phone",userdt.getPhone());
		queryWrapper.orderByDesc("createTime");
		IPage<Userdt> iPage = userdtService.page(page, queryWrapper);
		List<Userdt> userdts =iPage.getRecords();
		Long count = iPage.getTotal();
		result.setCode(0);
		result.setCount(count);
		result.setData(userdts);
		result.setMsg("获取成功");
		return result;
		
	}
	@RequestMapping("daoChu")
	public ReturnMap daoChu(Userdt userdt) {
		ReturnMap rm = new ReturnMap();
		QueryWrapper<Userdt> queryWrapper = new QueryWrapper<Userdt>();
		queryWrapper.setEntity(userdt);
		queryWrapper.select("userName", "phone","bm","zqnum","cwnum","createTime");
		List<Userdt> userdts= userdtService.list(queryWrapper);
		rm.setCode(1);
		rm.setData(userdts);
		rm.setMsg("获取成功");
		
		return rm;
	}

}
