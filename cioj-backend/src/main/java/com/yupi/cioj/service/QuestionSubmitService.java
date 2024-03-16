package com.yupi.cioj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.cioj.model.dto.question.QuestionQueryRequest;
import com.yupi.cioj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.cioj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.yupi.cioj.model.entity.Question;
import com.yupi.cioj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.cioj.model.entity.User;
import com.yupi.cioj.model.vo.QuestionSubmitVO;
import com.yupi.cioj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author 帅哥
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2024-03-14 10:33:02
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 提交题目
     *
     * @param questionId
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionId, User loginUser);



    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);


    /**
     * 获取题目提交封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目提交封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);



}
