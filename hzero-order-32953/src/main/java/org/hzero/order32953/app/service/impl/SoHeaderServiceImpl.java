package org.hzero.order32953.app.service.impl;

import io.choerodon.core.domain.Page;
import io.choerodon.core.exception.CommonException;
import io.choerodon.mybatis.pagehelper.PageHelper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.core.base.BaseAppService;
import org.hzero.core.base.BaseConstants;
import org.hzero.mybatis.helper.SecurityTokenHelper;
import org.hzero.order32953.app.service.SoHeaderService;
import org.hzero.order32953.domain.entity.SoHeader;
import org.hzero.order32953.domain.entity.SoLine;
import org.hzero.order32953.domain.repository.SoHeaderRepository;
import org.hzero.order32953.domain.repository.SoLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 销售订单头信息应用服务默认实现
 *
 * @author 32953 2021-08-12 20:42:40
 */
@Service
public class SoHeaderServiceImpl extends BaseAppService implements SoHeaderService {

    private final SoHeaderRepository soHeaderRepository;
    private final SoLineRepository soLineRepository;

    @Autowired
    public SoHeaderServiceImpl(SoHeaderRepository soHeaderRepository, SoLineRepository soLineRepository) {
        this.soHeaderRepository = soHeaderRepository;
        this.soLineRepository = soLineRepository;
    }

    @Override
    public Page<SoHeader> list(Long tenantId, SoHeader soHeader, PageRequest pageRequest) {
        String orderNumber = soHeader.getOrderNumber();
        return PageHelper.doPageAndSort(pageRequest, () -> {
            if (orderNumber != null) {
                soHeader.setOrderNumber("%" + orderNumber);
            }
            return soHeaderRepository.selectAllWithObject(soHeader);
        });
    }

    @Override
    public SoHeader getById(Long soHeaderId){
        return soHeaderRepository.selectSoHeader(soHeaderId);
    }

    @Override
    public SoHeader detail(Long tenantId, Long soHeaderId) {
        return soHeaderRepository.selectByPrimaryKey(soHeaderId);
    }

    @Override
    public SoHeader create(Long tenantId, SoHeader soHeader) {
        validObject(soHeader);
        soHeaderRepository.insertSelective(soHeader);
        return soHeader;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SoHeader update(Long tenantId, SoHeader soHeader) {
        SecurityTokenHelper.validToken(soHeader);
        soHeaderRepository.updateByPrimaryKeySelective(soHeader);
        return soHeader;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<SoHeader> soHeader) {
        SecurityTokenHelper.validToken(soHeader);
        soHeaderRepository.batchDeleteByPrimaryKey(soHeader);
    }

    @Override
    public void updateOrderStatus(Long soHeaderId, String status, String condition) {
        soHeaderRepository.updateStatus(soHeaderId, status, condition);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SoHeader soHeader) {
        // 拿到订单行
        List<SoLine> soLines = soHeader.getSoLines();
        // 校验订单头
        validObject(soHeader);
        // 插入订单头，并回写得到订单头ID
        soHeaderRepository.insertSelective(soHeader);
        Long soHeaderId = soHeader.getSoHeaderId();
        // 创建自增Long类型订单行号，从1开始
        AtomicReference<Long> line = new AtomicReference<>(1L);
        // 为每个订单行写入订单头ID和行号
        soLines.forEach(soLine -> {
            soLine.setSoHeaderId(soHeaderId);
            soLine.setLineNumber(line.get());
            line.updateAndGet(v -> v + 1);
        });
        // 校验订单行
        validObject(soLines);
        // 插入订单行
        soLineRepository.batchInsertSelective(soLines);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateWithLine(SoHeader soHeader) {
        // 拿到订单行
        List<SoLine> soLines = soHeader.getSoLines();
        // 校验token，判断是否为合法修改
        SecurityTokenHelper.validToken(soHeader);
        // 修改订单头
        soHeaderRepository.updateByPrimaryKeySelective(soHeader);
        // 修改订单行，为空的情况
        if (!soLines.isEmpty()) {
            soLineRepository.batchUpdateByPrimaryKeySelective(soLines);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(SoHeader soHeader) {
        // 校验token，判断是否为合法修改
        SecurityTokenHelper.validToken(soHeader);
        // 拿到订单头ID
        Long soHeaderId = soHeader.getSoHeaderId();
        /*
        * 根据 ID 删除订单头，当 ID 为空，相当于执行的 SQL 是 delete from table where id = null;
        * 所以还是建议判断是否为空
        * */
        if (soHeader.getSoHeaderId() == null) {
            throw new CommonException(BaseConstants.ErrorCode.DATA_NOT_EXISTS);
        } else {
            soHeaderRepository.deleteByPrimaryKey(soHeader);
            // 删除订单头下的所有订单行
            soLineRepository.batchDeleteBySoHeaderId(soHeaderId);
        }
    }
}
