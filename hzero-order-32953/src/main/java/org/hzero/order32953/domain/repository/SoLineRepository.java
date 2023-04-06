package org.hzero.order32953.domain.repository;

import org.hzero.mybatis.base.BaseRepository;

import org.hzero.order32953.domain.entity.SoLine;

import java.util.List;

/**
 * 销售订单行信息资源库
 *
 * @author 32953 2021-08-12 20:42:40
 */
public interface SoLineRepository extends BaseRepository<SoLine> {

    /**
     * 查询订单行和行内所属的物料
     *
     * @param soLine 查询条件，仅订单头id
     * @return 查询出的多个值
     */
    List<SoLine> selectLineWithItem(SoLine soLine);

    /**
     * 行号自增插入
     *
     * @param soLines 订单行
     */
    void batchInsertSelectiveLine(List<SoLine> soLines);

    /**
     * 得到每个订单的最大行号
     *
     * @param soHeaderId 头ID
     * @return 最大行号
     */
    Long selectMaxLineNumber(Long soHeaderId);

    /**
     * 删掉与订单头ID相关的行
     *
     * @param soHeaderId 订单头ID
     */
    void batchDeleteBySoHeaderId(Long soHeaderId);
}
