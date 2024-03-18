package com.yupi.cioj.judge.codesandbox;

import com.yupi.cioj.judge.codesandbox.impl.ExampleCodeSandBox;
import com.yupi.cioj.judge.codesandbox.impl.RemoteCodeSandBox;
import com.yupi.cioj.judge.codesandbox.impl.ThirdPartCodeSandBox;

/**
 * @Author: ruoci
 **/
public class CodeSandBoxFactory {

    /**
     * 创建代码沙箱实例
     * @param type
     * @return
     */
    public static CodeSandbox newInstance(String type){
        if ("example".equals(type)){
            return new ExampleCodeSandBox();
        } else if ("remote".equals(type)) {
            return new RemoteCodeSandBox();
        } else if ("thirdPart".equals(type)){
            return new ThirdPartCodeSandBox();
        } else {
            return new ExampleCodeSandBox();
        }
    }

}
