package com.yupi.cioj.judge;

import com.yupi.cioj.judge.strategy.DefaultJudgeStrategyImpl;
import com.yupi.cioj.judge.strategy.JavaLanguageJudgeStrategyImpl;
import com.yupi.cioj.judge.strategy.JudgeContext;
import com.yupi.cioj.judge.strategy.JudgeStrategy;
import com.yupi.cioj.model.dto.questionsubmit.JudgeInfo;
import com.yupi.cioj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * @Author: ruoci
 **/
@Service
public class JudgeManager {


    public JudgeInfo doJudge(JudgeContext judgeContext){

        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();

        JudgeStrategy judgeStrategy = new DefaultJudgeStrategyImpl();

        if ("java".equals(language)){
            judgeStrategy =  new JavaLanguageJudgeStrategyImpl();
        }

        return judgeStrategy.doJudge(judgeContext);
    }

}
