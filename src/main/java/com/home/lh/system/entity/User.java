package com.home.lh.system.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 刘浩
 * @since 2021-03-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends Model {

    private static final long serialVersionUID = 1L;

    private String id;

    private String user;

    private String pwd;


}
