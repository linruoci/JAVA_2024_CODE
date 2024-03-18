package com.yupi.cioj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.cioj.annotation.AuthCheck;
import com.yupi.cioj.common.BaseResponse;
import com.yupi.cioj.common.ErrorCode;
import com.yupi.cioj.common.ResultUtils;
import com.yupi.cioj.constant.UserConstant;
import com.yupi.cioj.exception.BusinessException;
import com.yupi.cioj.model.dto.question.QuestionQueryRequest;
import com.yupi.cioj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.cioj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.yupi.cioj.model.entity.Question;
import com.yupi.cioj.model.entity.QuestionSubmit;
import com.yupi.cioj.model.entity.User;
import com.yupi.cioj.model.vo.QuestionSubmitVO;
import com.yupi.cioj.service.QuestionSubmitService;
import com.yupi.cioj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 帖子点赞接口
 *
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return resultNum
     */
    @PostMapping("/")
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
                                               HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞1
        final User loginUser = userService.getLoginUser(request);
        long questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(questionSubmitId);
    }


    /**
     * 分页获取题目提交列表
     * 除了管理员外， 普通用户只能看到非答案， 提交代码等公开信息
     *
     * @param questionSubmitQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/question")
    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest,
                                                                         HttpServletRequest request) {
        long current = questionSubmitQueryRequest.getCurrent();
        long size = questionSubmitQueryRequest.getPageSize();

        Page<QuestionSubmit> questionPage = questionSubmitService.page(new Page<>(current, size),
                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
        User loginUser = userService.getLoginUser(request);
        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionPage, loginUser));
    }


}
