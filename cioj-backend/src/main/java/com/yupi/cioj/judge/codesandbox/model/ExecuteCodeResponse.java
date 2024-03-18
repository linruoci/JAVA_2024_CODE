package com.yupi.cioj.judge.codesandbox.model;


import com.yupi.cioj.model.dto.questionsubmit.JudgeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: ruoci
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeResponse {


    private List<String> outputList;
    private String message;

    private Integer status;

    private JudgeInfo judgeInfo;

}
