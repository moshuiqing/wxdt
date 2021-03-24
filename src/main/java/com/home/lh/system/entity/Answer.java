package com.home.lh.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

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
public class Answer extends Model {

    private static final long serialVersionUID = 1L;

    private String id;

    private String title;

    private String bs;
    
    private String tmid;
    
    @TableField(exist=false)
    private String tp;
    
    


}
