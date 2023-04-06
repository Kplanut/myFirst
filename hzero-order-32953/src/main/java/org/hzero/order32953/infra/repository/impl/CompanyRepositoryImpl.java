package org.hzero.order32953.infra.repository.impl;

import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;

import org.hzero.order32953.domain.entity.Company;
import org.hzero.order32953.domain.repository.CompanyRepository;
import org.hzero.order32953.infra.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 公司主数据 资源库实现
 *
 * @author 32953 2021-08-12 20:42:40
 */
@Component
public class CompanyRepositoryImpl extends BaseRepositoryImpl<Company> implements CompanyRepository {

}
