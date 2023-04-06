package org.hzero.order32953.app.service.impl;

import io.choerodon.mybatis.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.hzero.core.base.BaseAppService;

import org.hzero.mybatis.domian.Condition;
import org.hzero.mybatis.helper.SecurityTokenHelper;

import org.hzero.mybatis.util.Sqls;
import org.hzero.order32953.app.service.CustomerService;
import org.hzero.order32953.domain.entity.Customer;
import org.hzero.order32953.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

/**
 * 客户主数据应用服务默认实现
 *
 * @author 32953 2021-08-12 20:42:40
 */
@Service
public class CustomerServiceImpl extends BaseAppService implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Page<Customer> list(Long tenantId, Customer customer, PageRequest pageRequest) {
        return customerRepository.pageAndSort(pageRequest, customer);
    }

    @Override
    public Page<Customer> listLike(Long tenantId, Customer customer, PageRequest pageRequest) {
        return PageHelper.doPageAndSort(pageRequest, () -> {
                    // 拿到sql客制化
                    Sqls custom = Sqls.custom();
                    // 取出传入的编号和名字
                    String customerNumber = customer.getCustomerNumber();
                    String customerName = customer.getCustomerName();
                    // 判断是否为空值，空值则不添加Like查询条件
                    if (!StringUtils.isEmpty(customerNumber)) {
                        custom.andLike("customerNumber", customerNumber);
                    }
                    if (!StringUtils.isEmpty(customerName)) {
                        custom.andLike("customerName", customerName);
                    }
                    // 根据条件查询客户
                    return customerRepository.selectByCondition(
                            Condition.builder(Customer.class).andWhere(custom).build()
                    );
                }
        );
    }

    @Override
    public Customer detail(Long tenantId, Long customerId) {
        return customerRepository.selectByPrimaryKey(customerId);
    }

    @Override
    public Customer create(Long tenantId, Customer customer) {
        validObject(customer);
        customerRepository.insertSelective(customer);
        return customer;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Customer update(Long tenantId, Customer customer) {
        SecurityTokenHelper.validToken(customer);
        customerRepository.updateByPrimaryKeySelective(customer);
        return customer;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Customer customer) {
        SecurityTokenHelper.validToken(customer);
        customerRepository.deleteByPrimaryKey(customer);
    }
}
