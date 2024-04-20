package com.yupi.cioj.judge.strategy;

import com.yupi.cioj.model.dto.question.JudgeCase;
import com.yupi.cioj.judge.codesandbox.model.JudgeInfo;
import com.yupi.cioj.model.entity.Question;
import com.yupi.cioj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * @Author: ruoci
 **/
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> outputList;

    private List<String> inputList;

    private Question question;

    private QuestionSubmit questionSubmit;

    private List<JudgeCase> judgeCaseList;


}
