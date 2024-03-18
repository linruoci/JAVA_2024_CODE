package com.yupi.cioj.judge.codesandbox;

import com.yupi.cioj.judge.codesandbox.impl.ExampleCodeSandBox;
import com.yupi.cioj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.cioj.judge.codesandbox.model.ExecuteCodeResponse;
import com.yupi.cioj.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: ruoci
 **/
@SpringBootTest
class CodeSandboxTest {


    @Value("${codesandbox.type:example}")
    private String type;


    @Test
    void executeCode() {

        CodeSandbox codeSandbox = new ExampleCodeSandBox();

        List<String> inputList = Arrays.asList("1 2", "3 4");
        String code = "int main{}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();

        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .inputList(inputList)
                .language(language)
                .build();

        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);

    }

    @Test
    void executeCodeByFactory() {

        CodeSandbox codeSandbox = CodeSandBoxFactory.newInstance(type);

        List<String> inputList = Arrays.asList("1 2", "3 4");
        String code = "int main{}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();

        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .inputList(inputList)
                .language(language)
                .build();

        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);

    }

    @Test
    void executeCodeByProxy() {

        CodeSandbox codeSandbox = CodeSandBoxFactory.newInstance(type);
        CodeSandBoxProxy codeSandBoxProxy = new CodeSandBoxProxy(codeSandbox);

        List<String> inputList = Arrays.asList("1 2", "3 4");
        String code = "int main{}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();

        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .inputList(inputList)
                .language(language)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandBoxProxy.executeCode(executeCodeRequest);

    }





}