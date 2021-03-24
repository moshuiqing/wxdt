
package com.home.lh.system.controller;
 
 
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.home.lh.system.entity.User;
import com.home.lh.system.service.IUserService;
import com.home.lh.util.po.ReturnMap;

 
/**
 * <p>
    *  前端控制器
    * </p>
 *
 * @author 刘浩
 * @since 2021-03-22
 * @version v1.0
 */

@RestController
@RequestMapping("/user")
public class UserController {
  
    @Autowired
    private IUserService userService;
    
    
    /**
     * @param user
     * @return
     */
    @RequestMapping("login")
    public ReturnMap login(User user) {
    	ReturnMap rm = new ReturnMap();
    	if(StringUtils.isEmpty(user.getUser())) {
    		rm.setCode(-1);
    		rm.setMsg("请输入用户名");
    		
    	}else {
    		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    		queryWrapper.eq("user", user.getUser());
    		User u= userService.getOne(queryWrapper);
    		if(u==null) {
    			rm.setCode(-1);
    			rm.setMsg("用户不存在");
    			
    		}else {
    			String pwd = DigestUtils.md5Hex(user.getPwd());
    			
    			if(u.getPwd().equalsIgnoreCase(pwd)) {
    				rm.setCode(1);
    				rm.setData(user);
    				rm.setMsg("登录成功");
    			}else {
    				rm.setCode(-1);
    				rm.setMsg("密码不正确");
    			}   			
    		}
    	}
    	
    	
		return rm;
    }
 
 
}
