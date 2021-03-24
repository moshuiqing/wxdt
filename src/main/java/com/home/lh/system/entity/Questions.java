package com.home.lh.system.entity;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
public class Questions extends Model {

    private static final long serialVersionUID = 1L;

    private String id;

    private String tm;

    private String da;

    @TableLogic
    private String isdel;
    
    @TableField(exist=false)
    private List<Answer> answers;
    
    @TableField(exist=false)
    private String jsonAs;
    
    private Date createtime;
    
    /**
     * 1 单选  2 多选
     */
    private String type;
    
    /**
     * 选择的答案
     */
    @TableField(exist=false)
    private String checks;


}
