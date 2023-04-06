package org.hzero.order32953.infra.mapper;

import org.hzero.order32953.domain.entity.SoHeader;
import io.choerodon.mybatis.common.BaseMapper;

import java.util.List;

/**
 * 销售订单头信息Mapper
 *
 * @author 32953 2021-08-12 20:42:40
 */
public interface SoHeaderMapper extends BaseMapper<SoHeader> {

    /**
     * 根据id查询
     *
     * @param soHeaderId id条件
     * @return 查询结果
     */
    SoHeader selectHeader(Long soHeaderId);

    /**
     * 修改订单状态
     *
     * @param soHeaderId 订单ID
     * @param status 目标状态
     * @param condition 当前状态
     */
    void updateStatus(Long soHeaderId, String status, String condition);

    /**
     * 拿到订单相关的所有数据
     *
     * @return 订单详情
     */
    List<SoHeader> selectEverything(SoHeader soHeader);
}
