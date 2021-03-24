package com.home.lh.system.service.impl;

import com.home.lh.system.entity.Questions;
import com.home.lh.system.mapper.QuestionsMapper;
import com.home.lh.system.service.IQuestionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 刘浩
 * @since 2021-02-08
 */
@Service
public class QuestionsServiceImpl extends ServiceImpl<QuestionsMapper, Questions> implements IQuestionsService {

}
