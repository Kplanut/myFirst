package org.hzero.order32953.app.service;

import org.hzero.order32953.domain.entity.SoLine;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

import java.util.List;

/**
 * 销售订单行信息应用服务
 *
 * @author 32953 2021-08-12 20:42:40
 */
public interface SoLineService {

    /**
     * 销售订单行信息查询参数
     *
     * @param tenantId    租户ID
     * @param soLine      销售订单行信息
     * @param pageRequest 分页
     * @return 销售订单行信息列表
     */
    Page<SoLine> list(Long tenantId, SoLine soLine, PageRequest pageRequest);

    /**
     * 订单行和物料的连接查询
     *
     * @param soLine 查询条件，仅订单头ID
     * @param pageRequest 分页
     * @return 封装的分页后的结果
     */
    Page<SoLine> listLineWithItem(SoLine soLine, PageRequest pageRequest);

    /**
     * 销售订单行信息详情
     *
     * @param tenantId 租户ID
     * @param soLineId 主键
     * @return 销售订单行信息列表
     */
    SoLine detail(Long tenantId, Long soLineId);

    /**
     * 创建销售订单行信息
     *
     * @param tenantId 租户ID
     * @param soLines   销售订单行信息
     * @return 销售订单行信息
     */
    List<SoLine> create(Long tenantId, List<SoLine> soLines);

    /**
     * 更新销售订单行信息
     *
     * @param tenantId 租户ID
     * @param soLine   销售订单行信息
     * @return 销售订单行信息
     */
    List<SoLine> update(Long tenantId, List<SoLine> soLine);

    /**
     * 删除销售订单行信息
     *
     * @param soLine 销售订单行信息
     */
    void remove(List<SoLine> soLine);
}
