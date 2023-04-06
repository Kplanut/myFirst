package org.hzero.order32953.api.controller.v1;

import io.swagger.annotations.Api;
import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;

import org.hzero.order32953.app.service.CustomerService;
import org.hzero.order32953.config.SwaggerTags;
import org.hzero.order32953.domain.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.choerodon.swagger.annotation.Permission;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 客户主数据 管理 API
 *
 * @author 32953 2021-08-12 20:42:40
 */
@Api(tags = SwaggerTags.CUSTOMER)
@RestController("customerController.v1")
@RequestMapping("/v1/{organizationId}/customers")
public class CustomerController extends BaseController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "客户主数据列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping
    public ResponseEntity<Page<Customer>> list(@PathVariable("organizationId") Long organizationId, Customer customer, @ApiIgnore @SortDefault(value = Customer.FIELD_CUSTOMER_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<Customer> list = customerService.list(organizationId, customer, pageRequest);
        return Results.success(list);
    }

    @ApiOperation(value = "客户主数据明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> detail(@PathVariable("organizationId") Long organizationId, @PathVariable Long customerId) {
        Customer customer = customerService.detail(organizationId, customerId);
        return Results.success(customer);
    }

    @ApiOperation(value = "客户主数据模糊查询，根据客户编号和客户名称")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/lov")
    public ResponseEntity<Page<Customer>> listLike(@PathVariable("organizationId") Long organizationId, Customer customer, @ApiIgnore @SortDefault(value = Customer.FIELD_CUSTOMER_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<Customer> list = customerService.listLike(organizationId, customer, pageRequest);
        return Results.success(list);
    }

    @ApiOperation(value = "创建客户主数据")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<Customer> create(@PathVariable("organizationId") Long organizationId, @RequestBody Customer customer) {
        customerService.create(organizationId, customer);
        return Results.success(customer);
    }

    @ApiOperation(value = "修改客户主数据")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping
    public ResponseEntity<Customer> update(@PathVariable("organizationId") Long organizationId, @RequestBody Customer customer) {
        customerService.update(organizationId, customer);
        return Results.success(customer);
    }

    @ApiOperation(value = "删除客户主数据")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody Customer customer) {
        customerService.remove(customer);
        return Results.success();
    }

}
