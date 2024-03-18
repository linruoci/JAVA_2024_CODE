package com.yupi.cioj.judge.codesandbox;

import com.yupi.cioj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.cioj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @Author: ruoci
 **/
public interface CodeSandbox {


    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);

}
