package org.hzero.order32953.app.service;

import org.hzero.order32953.domain.entity.Customer;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

/**
 * 客户主数据应用服务
 *
 * @author 32953 2021-08-12 20:42:40
 */
public interface CustomerService {

    /**
     * 客户主数据查询参数
     *
     * @param tenantId    租户ID
     * @param customer    客户主数据
     * @param pageRequest 分页
     * @return 客户主数据列表
     */
    Page<Customer> list(Long tenantId, Customer customer, PageRequest pageRequest);

    /**
     * 客户主数据根据客户编码和名称模糊查询
     *
     * @param tenantId    租户ID
     * @param customer    客户主数据
     * @param pageRequest 分页
     * @return 模糊查询的结果
     */
    Page<Customer> listLike(Long tenantId, Customer customer, PageRequest pageRequest);


    /**
     * 客户主数据详情
     *
     * @param tenantId   租户ID
     * @param customerId 主键
     * @return 客户主数据列表
     */
    Customer detail(Long tenantId, Long customerId);

    /**
     * 创建客户主数据
     *
     * @param tenantId 租户ID
     * @param customer 客户主数据
     * @return 客户主数据
     */
    Customer create(Long tenantId, Customer customer);

    /**
     * 更新客户主数据
     *
     * @param tenantId 租户ID
     * @param customer 客户主数据
     * @return 客户主数据
     */
    Customer update(Long tenantId, Customer customer);

    /**
     * 删除客户主数据
     *
     * @param customer 客户主数据
     */
    void remove(Customer customer);
}
