package com.yupi.cioj.judge.codesandbox.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.yupi.cioj.common.ErrorCode;
import com.yupi.cioj.exception.BusinessException;
import com.yupi.cioj.judge.codesandbox.CodeSandbox;
import com.yupi.cioj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.cioj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @Author: ruoci
 **/
public class RemoteCodeSandBox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {

        String url = "http://localhost:8082/executeCode";
        String json = JSONUtil.toJsonStr(executeCodeRequest);
        String responseStr = HttpUtil.createPost(url)
                .body(json)
                .execute()
                .body();

        if (StrUtil.isBlank(responseStr)){
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR, "executeCode remoteSandbox error, message = "+ responseStr);
        }
        return JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);


    }
}
