package com.ruoci.springbootinit.bizmq;

import com.rabbitmq.client.Channel;
import com.ruoci.springbootinit.common.ErrorCode;
import com.ruoci.springbootinit.constant.CommonConstant;
import com.ruoci.springbootinit.exception.BusinessException;
import com.ruoci.springbootinit.exception.ThrowUtils;
import com.ruoci.springbootinit.manager.AiManager;
import com.ruoci.springbootinit.model.entity.Chart;
import com.ruoci.springbootinit.service.ChartService;
import com.ruoci.springbootinit.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author: ruoci
 **/
@Component
@Slf4j
public class BiConsumer {


    @Resource
    private ChartService chartService;

    @Resource
    private AiManager aiManager;

    @RabbitListener(queues = CommonConstant.BI_QUEUE_NAME, ackMode = "MANUAL")
    public void receiveMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        try {
            if (StringUtils.isBlank(message)){
                log.error("信息id为空!");
                channel.basicNack(deliveryTag, false, false);
            }
            long chartId = Long.parseLong(message);
            Chart chart = chartService.getById(chartId);
            log.info("receive message: {}", message);

//        先更新状态
            Chart updateChart = new Chart();
            updateChart.setId(chart.getId());
            updateChart.setStatus("running");
            updateChart.setExecMessage("正在进行分析!");
            boolean b = chartService.updateById(updateChart);
            if (!b){
                log.error("更新运行状态失败!");
                channel.basicNack(deliveryTag, false, false);
                chartService.handleUpdateError(chart.getId(), "更新运行状态失败!");
            }

            String content = aiManager.doChat(CommonConstant.MODEL_ID, buildUserInput(chart));
//        切分一下.
            String[] split = content.split("【【【【【");
            ThrowUtils.throwIf(split.length < 3, new BusinessException(ErrorCode.SYSTEM_ERROR, "Ai生成错误!!"));
            String genChart = split[1].trim();
            String genResult = split[2].trim();
            Chart chartUpdateResult = new Chart();
            chartUpdateResult.setId(chart.getId());
            chartUpdateResult.setGenChart(genChart);
            chartUpdateResult.setGenResult(genResult);
            chartUpdateResult.setStatus("succeed");
            chartUpdateResult.setExecMessage("图表生成成功!");
            b = chartService.updateById(chartUpdateResult);
            if (!b){
                log.error("更新图表数据失败!");
                channel.basicNack(deliveryTag, false, false);
                chartService.handleUpdateError(chart.getId(), "更新图表数据时失败!");
            }
        } catch (Exception e) {
            log.error("图表执行失败!");
            throw new RuntimeException(e);
        }

    }


    private String buildUserInput(Chart chart){
        StringBuilder userInput = new StringBuilder();


        String goal = chart.getGoal();
        String chartType = chart.getChartType();
        String chartData = chart.getChartData();

        userInput.append("分析需求:").append(goal).append("\n");
        String userGoal = goal;
        if (StringUtils.isNotBlank(chartType)){
            userGoal += " 请使用" + chartType;
        }
        userInput.append(userGoal).append("\n");
        userInput.append("原始数据:").append("\n");
        userInput.append(chartData).append("\n");
        return userInput.toString();

    }



}
