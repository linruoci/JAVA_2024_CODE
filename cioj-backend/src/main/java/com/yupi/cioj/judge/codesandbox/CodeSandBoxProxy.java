package com.yupi.cioj.judge.codesandbox;

import com.yupi.cioj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.cioj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: ruoci
 **/
@Slf4j
@AllArgsConstructor
public class CodeSandBoxProxy implements CodeSandbox{

    private CodeSandbox codeSandbox;

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("请求信息:" + executeCodeRequest.toString());
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        log.info("响应信息: " + executeCodeResponse.toString());
        return executeCodeResponse;
    }
}
