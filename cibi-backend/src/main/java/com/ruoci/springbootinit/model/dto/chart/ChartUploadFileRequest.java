package com.ruoci.springbootinit.model.dto.chart;

import lombok.Data;

/**
 * @Author: ruoci
 **/
@Data
public class ChartUploadFileRequest {


    /**
     * 分析目标
     */
    private String goal;
    /**
     * 图标名称
     */
    private String name;

    /**
     * 图表类型
     */
    private String chartType;

}
