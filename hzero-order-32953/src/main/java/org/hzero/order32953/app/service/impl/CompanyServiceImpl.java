package org.hzero.order32953.app.service.impl;

import io.choerodon.mybatis.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.hzero.core.base.BaseAppService;

import org.hzero.mybatis.domian.Condition;
import org.hzero.mybatis.helper.SecurityTokenHelper;

import org.hzero.mybatis.util.Sqls;
import org.hzero.order32953.app.service.CompanyService;
import org.hzero.order32953.domain.entity.Company;
import org.hzero.order32953.domain.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;


/**
 * 公司主数据应用服务默认实现
 *
 * @author 32953 2021-08-12 20:42:40
 */
@Service
public class CompanyServiceImpl extends BaseAppService implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Page<Company> list(Long tenantId, Company company, PageRequest pageRequest) {
        return companyRepository.pageAndSort(pageRequest, company);
    }

    @Override
    public Page<Company> listLike(Long tenantId, Company company, PageRequest pageRequest) {
        return PageHelper.doPageAndSort(pageRequest, () -> {
                    // 拿到sql客制化
                    Sqls custom = Sqls.custom();
                    // 取出传入的编号和名字
                    String companyNumber = company.getCompanyNumber();
                    String companyName = company.getCompanyName();
                    // 判断是否为空值，空值则不添加Like查询条件
                    if (!StringUtils.isEmpty(companyNumber)) {
                        custom.andLike("companyNumber", companyNumber);
                    }
                    if (!StringUtils.isEmpty(companyName)) {
                        custom.andLike("companyName", companyName);
                    }
                    // 根据条件查询公司
                    return companyRepository.selectByCondition(
                            Condition.builder(Company.class).andWhere(custom).build()
                    );
                }
        );
    }

    @Override
    public Company detail(Long tenantId, Long companyId) {
        return companyRepository.selectByPrimaryKey(companyId);
    }

    @Override
    public Company create(Long tenantId, Company company) {
        validObject(company);
        companyRepository.insertSelective(company);
        return company;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Company update(Long tenantId, Company company) {
        SecurityTokenHelper.validToken(company);
        companyRepository.updateByPrimaryKeySelective(company);
        return company;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Company company) {
        SecurityTokenHelper.validToken(company);
        companyRepository.deleteByPrimaryKey(company);
    }
}
