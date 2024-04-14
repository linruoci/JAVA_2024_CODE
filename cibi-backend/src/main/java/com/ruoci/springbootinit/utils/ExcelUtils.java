package com.ruoci.springbootinit.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: ruoci
 **/
@Slf4j
public class ExcelUtils {


    public static String excelToCsv(MultipartFile multipartFile){

        List<Map<Integer, String>> list = null;
        try {
            list = EasyExcel.read(multipartFile.getInputStream())
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet()
                    .headRowNumber(0)
                    .doReadSync();
        } catch (IOException e) {
            log.error("表格处理错误!");
            e.printStackTrace();
        }
//        1。读取表头
        StringBuilder stringBuilder = new StringBuilder();

        LinkedHashMap<Integer, String> headerMap = (LinkedHashMap<Integer, String>) list.get(0);
        List<String> headerList = headerMap.values().stream().filter(header -> ObjectUtils.isNotEmpty(header)).collect(Collectors.toList());
        String header = StringUtils.join(headerList, ",");
        stringBuilder.append(header).append("\n");


//        2.读取数据.
        for (int i = 1; i < list.size(); i++){
            LinkedHashMap<Integer, String> valueMap = (LinkedHashMap<Integer, String>) list.get(i);
            List<String> valueList = valueMap.values().stream().filter(value -> ObjectUtils.isNotEmpty(value)).collect(Collectors.toList());
            String value = StringUtils.join(valueList, ",");
            stringBuilder.append(value).append("\n");

        }

        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }







}
