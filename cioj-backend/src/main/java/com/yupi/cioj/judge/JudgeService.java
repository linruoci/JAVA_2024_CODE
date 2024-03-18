package com.yupi.cioj.judge;

import com.yupi.cioj.model.entity.QuestionSubmit;

/**
 * @Author: ruoci
 **/
public interface JudgeService {


    QuestionSubmit doJudge(long questionSubmitId);

}
