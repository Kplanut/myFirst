package org.hzero.order32953.domain.repository;

import org.hzero.mybatis.base.BaseRepository;

import org.hzero.order32953.domain.entity.SoHeader;

import java.util.List;

/**
 * 销售订单头信息资源库
 *
 * @author 32953 2021-08-12 20:42:40
 */
public interface SoHeaderRepository extends BaseRepository<SoHeader> {

    /**
     * 根据id得到表单头
     *
     * @param soHeaderId 表单头id
     * @return 查询结果
     */
    SoHeader selectSoHeader(Long soHeaderId);

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
    List<SoHeader> selectAllWithObject(SoHeader soHeader);
}
