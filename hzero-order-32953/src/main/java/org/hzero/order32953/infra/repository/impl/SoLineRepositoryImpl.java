package org.hzero.order32953.infra.repository.impl;

import org.hzero.mybatis.base.impl.BaseRepositoryImpl;

import org.hzero.order32953.domain.entity.SoLine;
import org.hzero.order32953.domain.repository.SoLineRepository;
import org.hzero.order32953.infra.mapper.SoLineMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 销售订单行信息 资源库实现
 *
 * @author 32953 2021-08-12 20:42:40
 */
@Component
public class SoLineRepositoryImpl extends BaseRepositoryImpl<SoLine> implements SoLineRepository {

    private final SoLineMapper soLineMapper;

    public SoLineRepositoryImpl(SoLineMapper soLineMapper) {
        this.soLineMapper = soLineMapper;
    }

    @Override
    public List<SoLine> selectLineWithItem(SoLine soLine) {
        return soLineMapper.selectLineWithItem(soLine);
    }

    @Override
    public void batchInsertSelectiveLine(List<SoLine> soLines) {
        soLineMapper.batchInsertSelectiveLine(soLines);
    }

    @Override
    public Long selectMaxLineNumber(Long soHeaderId) {
        return soLineMapper.selectMaxLineNumber(soHeaderId);
    }

    @Override
    public void batchDeleteBySoHeaderId(Long soHeaderId) {
        soLineMapper.batchDeleteBySoHeaderId(soHeaderId);
    }

}
