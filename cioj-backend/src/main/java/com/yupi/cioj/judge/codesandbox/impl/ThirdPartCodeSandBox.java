package com.yupi.cioj.judge.codesandbox.impl;

import com.yupi.cioj.judge.codesandbox.CodeSandbox;
import com.yupi.cioj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.cioj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @Author: ruoci
 **/
public class ThirdPartCodeSandBox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("thirdPart");
        return null;
    }
}
