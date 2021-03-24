package com.home.lh.mapi;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.home.lh.system.entity.Userdt;
import com.home.lh.system.service.IUserdtService;
import com.home.lh.util.po.ReturnMap;

@RestController
@RequestMapping("/mapi/userdt/")
public class UserDtMapi {
	
	@Autowired
	private IUserdtService userdtService;
	
	
	/**
	 * @param userdt
	 * @return
	 */
	@RequestMapping("saveUserDt")
	public ReturnMap saveUserDt(Userdt userdt) {
		ReturnMap rm = new ReturnMap();
		userdt.setId(UUID.randomUUID().toString());
		userdt.setCreateTime(LocalDateTime.now());
		Boolean flag= userdtService.save(userdt);
		if(flag) {
			rm.setCode(1);
			rm.setMsg("提交成功");			
		}else {
			rm.setCode(-1);
			rm.setMsg("提交失败");
		}
		return rm;
	}

}
