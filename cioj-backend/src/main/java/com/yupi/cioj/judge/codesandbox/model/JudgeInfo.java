package com.yupi.cioj.judge.codesandbox.model;

import lombok.Data;

/**
 * @Author: ruoci
 **/
@Data
public class JudgeInfo {

    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 空间限制(kb)
     */
    private Long memory;
    /**
     * 堆栈限制.(kb)
     */
    private Long time;

}
