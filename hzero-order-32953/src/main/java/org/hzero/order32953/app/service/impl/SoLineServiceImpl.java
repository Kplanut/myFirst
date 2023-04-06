package org.hzero.order32953.app.service.impl;

import io.choerodon.mybatis.pagehelper.PageHelper;
import org.hzero.core.base.BaseAppService;

import org.hzero.mybatis.helper.SecurityTokenHelper;

import org.hzero.order32953.app.service.SoLineService;
import org.hzero.order32953.domain.entity.SoLine;
import org.hzero.order32953.domain.repository.SoLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 销售订单行信息应用服务默认实现
 *
 * @author 32953 2021-08-12 20:42:40
 */
@Service
public class SoLineServiceImpl extends BaseAppService implements SoLineService {

    private final SoLineRepository soLineRepository;

    @Autowired
    public SoLineServiceImpl(SoLineRepository soLineRepository) {
        this.soLineRepository = soLineRepository;
    }

    @Override
    public Page<SoLine> list(Long tenantId, SoLine soLine, PageRequest pageRequest) {
        return soLineRepository.pageAndSort(pageRequest, soLine);
    }

    @Override
    public Page<SoLine> listLineWithItem(SoLine soLine, PageRequest pageRequest) {
        // 查询携带了item类信息的订单行，并分页和排序
        return PageHelper.doPageAndSort(pageRequest,() -> soLineRepository.selectLineWithItem(soLine));
    }

    @Override
    public SoLine detail(Long tenantId, Long soLineId) {
        return soLineRepository.selectByPrimaryKey(soLineId);
    }

    @Override
    public List<SoLine> create(Long tenantId, List<SoLine> soLines) {
        // 校验订单行
        validObject(soLines);
        // 得到最大行数
        Long maxLine = soLineRepository.selectMaxLineNumber(soLines.get(0).getSoHeaderId());
        // 创建自增Long类型，开始数值为最大行数
        AtomicReference<Long> line = new AtomicReference<>(maxLine);
        // 设置新增的的单行的行号为最大行号+1，并递增插入
        soLines.forEach(soLine -> {
            line.updateAndGet(v -> v + 1L);
            soLine.setLineNumber(line.get());
        });
        // 插入订单行
        soLineRepository.batchInsertSelective(soLines);
        return soLines;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<SoLine> update(Long tenantId, List<SoLine> soLine) {
        SecurityTokenHelper.validToken(soLine);
        soLineRepository.batchUpdateByPrimaryKeySelective(soLine);
        return soLine;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<SoLine> soLine) {
        SecurityTokenHelper.validToken(soLine);
        soLineRepository.batchDeleteByPrimaryKey(soLine);
    }
}
