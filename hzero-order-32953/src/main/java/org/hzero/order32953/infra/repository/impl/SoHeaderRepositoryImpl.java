package org.hzero.order32953.infra.repository.impl;

import org.hzero.mybatis.base.impl.BaseRepositoryImpl;

import org.hzero.order32953.domain.entity.SoHeader;
import org.hzero.order32953.domain.repository.SoHeaderRepository;
import org.hzero.order32953.infra.mapper.SoHeaderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 销售订单头信息 资源库实现
 *
 * @author 32953 2021-08-12 20:42:40
 */
@Component
public class SoHeaderRepositoryImpl extends BaseRepositoryImpl<SoHeader> implements SoHeaderRepository {

    private final SoHeaderMapper soHeaderMapper;

    @Autowired
    public SoHeaderRepositoryImpl(SoHeaderMapper soHeaderMapper) {
        this.soHeaderMapper = soHeaderMapper;
    }

    @Override
    public SoHeader selectSoHeader(Long soHeaderId) {
        return soHeaderMapper.selectHeader(soHeaderId);
    }

    @Override
    public void updateStatus(Long soHeaderId, String status, String condition) {
        soHeaderMapper.updateStatus(soHeaderId, status, condition);
    }

    @Override
    public List<SoHeader> selectAllWithObject(SoHeader soHeader) {
        return soHeaderMapper.selectEverything(soHeader);
    }

}
