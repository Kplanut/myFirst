package org.hzero.order32953.api.controller.v1.common;

import lombok.extern.slf4j.Slf4j;
import org.hzero.boot.scheduler.infra.annotation.JobHandler;
import org.hzero.boot.scheduler.infra.enums.ReturnT;
import org.hzero.boot.scheduler.infra.handler.IJobHandler;
import org.hzero.boot.scheduler.infra.tool.SchedulerTool;
import org.hzero.order32953.app.service.SoHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 处理调度任务，在每天3点会执行一次execute方法
 *
 * @author 32953 2021-08-18 15:10:02
 */
@Slf4j
@Component
@JobHandler("orderStatusClose")
public class Scheduler implements IJobHandler {

    private final SoHeaderService soHeaderService;

    @Autowired
    public Scheduler(SoHeaderService soHeaderService) {
        this.soHeaderService = soHeaderService;
    }

    @Override
    public ReturnT execute(Map<String, String> map, SchedulerTool tool) {
        // 将所有的状态为已通过的订单修改为已关闭
        soHeaderService.updateOrderStatus(null, "CLOSED", "APPROVED");
        tool.info("通过的订单已关闭");
        return ReturnT.SUCCESS;
    }
}
