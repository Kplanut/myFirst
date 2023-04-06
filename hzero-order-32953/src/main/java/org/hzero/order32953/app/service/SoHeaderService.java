package org.hzero.order32953.app.service;

import org.hzero.order32953.domain.entity.SoHeader;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

import java.util.List;

/**
 * 销售订单头信息应用服务
 *
 * @author 32953 2021-08-12 20:42:40
 */
public interface SoHeaderService {

    /**
     * 销售订单头信息查询参数
     *
     * @param tenantId    租户ID
     * @param soHeader    销售订单头信息
     * @param pageRequest 分页
     * @return 销售订单头信息列表
     */
    Page<SoHeader> list(Long tenantId, SoHeader soHeader, PageRequest pageRequest);

    /**
     * 根据id得到订单头和行
     *
     * @param soHeaderId 订单头id
     * @return 订单详细数据
     */
    SoHeader getById(Long soHeaderId);
    /**
     * 销售订单头信息详情
     *
     * @param tenantId   租户ID
     * @param soHeaderId 主键
     * @return 销售订单头信息列表
     */
    SoHeader detail(Long tenantId, Long soHeaderId);

    /**
     * 创建销售订单头信息
     *
     * @param tenantId 租户ID
     * @param soHeader 销售订单头信息
     * @return 销售订单头信息
     */
    SoHeader create(Long tenantId, SoHeader soHeader);

    /**
     * 更新销售订单头信息
     *
     * @param tenantId 租户ID
     * @param soHeader 销售订单头信息
     * @return 销售订单头信息
     */
    SoHeader update(Long tenantId, SoHeader soHeader);

    /**
     * 删除销售订单头信息
     *
     * @param soHeader 销售订单头信息
     */
    void remove(List<SoHeader> soHeader);

    /**
     * 修改订单状态
     *
     * @param soHeaderId 订单ID
     * @param status 目标状态
     * @param condition 目前状态
     */
    void updateOrderStatus(Long soHeaderId, String status, String condition);

    /**
     * 保存订单头和订单行
     *
     * @param soHeader 包含了订单行的订单头
     */
    void save(SoHeader soHeader);

    /**
     * 修改订单头和订单行
     *
     * @param soHeader 包含了订单行的订单头
     */
    void updateWithLine(SoHeader soHeader);

    /**
     * 整单删除
     *
     * @param soHeader 传入的带行的订单头
     */
    void delete(SoHeader soHeader);
}
