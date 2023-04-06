package org.hzero.order32953.app.service;

import org.hzero.order32953.domain.entity.Company;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

/**
 * 公司主数据应用服务
 *
 * @author 32953 2021-08-12 20:42:40
 */
public interface CompanyService {

    /**
     * 公司主数据查询参数
     *
     * @param tenantId    租户ID
     * @param company     公司主数据
     * @param pageRequest 分页
     * @return 公司主数据列表
     */
    Page<Company> list(Long tenantId, Company company, PageRequest pageRequest);

    /**
     * 公司主数据根据公司编码和名称模糊查询
     *
     * @param tenantId    租户ID
     * @param company     公司主数据
     * @param pageRequest 分页
     * @return 模糊查询的结果
     */
    Page<Company> listLike(Long tenantId, Company company, PageRequest pageRequest);

    /**
     * 公司主数据详情
     *
     * @param tenantId  租户ID
     * @param companyId 主键
     * @return 公司主数据列表
     */
    Company detail(Long tenantId, Long companyId);

    /**
     * 创建公司主数据
     *
     * @param tenantId 租户ID
     * @param company  公司主数据
     * @return 公司主数据
     */
    Company create(Long tenantId, Company company);

    /**
     * 更新公司主数据
     *
     * @param tenantId 租户ID
     * @param company  公司主数据
     * @return 公司主数据
     */
    Company update(Long tenantId, Company company);

    /**
     * 删除公司主数据
     *
     * @param company 公司主数据
     */
    void remove(Company company);
}
