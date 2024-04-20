package com.ruoci.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoci.springbootinit.common.ErrorCode;
import com.ruoci.springbootinit.exception.BusinessException;
import com.ruoci.springbootinit.model.entity.Chart;
import com.ruoci.springbootinit.service.ChartService;
import com.ruoci.springbootinit.mapper.ChartMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ChartServiceImpl extends ServiceImpl<ChartMapper, Chart>
    implements ChartService{

    public void handleUpdateError(long chartId, String execMessage){
        Chart chart = new Chart();
        chart.setId(chartId);
        chart.setStatus("fail");
        chart.setExecMessage(execMessage);
        boolean b = updateById(chart);

        if (!b){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "系统异常! 无法更新更新失败时的状态!");
        }

    }
}




