package com.ruoci.springbootinit.model.dto.chart;
import lombok.Data;
import java.io.Serializable;

/**
 * 编辑请求
 * @author linruoci
 */
@Data
public class ChartEditRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 分析目标
     */
    private String goal;



    /**
     * 图标名称
     */
    private String name;

    /**
     * 图表数据
     */
    private String chartData;

    /**
     * 图表类型
     */
    private String chartType;

    private static final long serialVersionUID = 1L;
}