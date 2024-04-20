package com.ruoci.springbootinit.service;

import com.ruoci.springbootinit.model.entity.Chart;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface ChartService extends IService<Chart> {

    /**
     * 处理更新图标状态时异常情况.
     * @param chartId
     * @param execMessage
     */
    void handleUpdateError(long chartId, String execMessage);
}
