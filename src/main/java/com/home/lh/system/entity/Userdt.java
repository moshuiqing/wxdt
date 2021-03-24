package com.home.lh.system.entity;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 刘浩
 * @since 2021-02-08
 */
@Data
@EqualsAndHashCode(callSuper = true)

public class Userdt extends Model {

	private static final long serialVersionUID = 1L;

	private String id;

	@TableField("userName")
	private String userName;

	private String phone;

	private String bm;

	private Integer zqnum;

	private Integer cwnum;

	@TableField("createTime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //接收前端
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss") //传给前端
	private LocalDateTime createTime;

}
