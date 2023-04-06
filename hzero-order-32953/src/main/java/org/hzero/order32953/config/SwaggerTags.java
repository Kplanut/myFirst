package org.hzero.order32953.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.Tag;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger Api 描述配置
 */
@Configuration
public class SwaggerTags {

    public static final String COMPANY = "company";
    public static final String CUSTOMER = "customer";
    public static final String ITEM = "item";
    public static final String SO_HEADER = "so_header";
    public static final String SO_LINE = "so_line";
    public static final String ORDER_DTO = "order_dto";
    public static final String SCHEDULER = "scheduler";

    @Autowired
    public SwaggerTags(Docket docket) {
        docket.tags(
                new Tag(COMPANY, "公司主数据"),
                new Tag(CUSTOMER, "客户主数据"),
                new Tag(ITEM, "物料主数据"),
                new Tag(SO_HEADER, "销售订单头信息"),
                new Tag(SO_LINE, "销售订单行信息"),
                new Tag(ORDER_DTO, "前端表格模拟数据"),
                new Tag(SCHEDULER, "调度任务")
        );
    }
}
