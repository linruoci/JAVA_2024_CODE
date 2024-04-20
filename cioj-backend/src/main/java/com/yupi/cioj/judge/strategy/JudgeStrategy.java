package com.yupi.cioj.judge.strategy;

import com.yupi.cioj.judge.codesandbox.model.JudgeInfo;

/**
 * @Author: ruoci
 * 判题策略
 **/
public interface JudgeStrategy {


    JudgeInfo doJudge(JudgeContext judgeContext);

}
