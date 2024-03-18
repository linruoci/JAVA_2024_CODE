package com.yupi.cioj.judge.codesandbox.impl;

import com.yupi.cioj.judge.codesandbox.CodeSandbox;
import com.yupi.cioj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.cioj.judge.codesandbox.model.ExecuteCodeResponse;
import com.yupi.cioj.model.dto.questionsubmit.JudgeInfo;
import com.yupi.cioj.model.enums.JudgeInfoMessageEnum;
import com.yupi.cioj.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * @Author: ruoci
 **/
public class ExampleCodeSandBox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();


        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());
        judgeInfo.setTime(100L);
        judgeInfo.setMemory(100L);

        ExecuteCodeResponse executeCodeResponse = ExecuteCodeResponse.builder()
                .outputList(inputList)
                .message("测试执行成功")
                .status(QuestionSubmitStatusEnum.SUCCEED.getValue())
                .judgeInfo(judgeInfo)
                .build();
        return executeCodeResponse;

    }
}
