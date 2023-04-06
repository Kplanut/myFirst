package org.hzero.order32953.api.controller.v1;

import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hzero.core.base.BaseController;
import org.hzero.core.util.Results;
import org.hzero.order32953.app.service.CompanyService;
import org.hzero.order32953.config.SwaggerTags;
import org.hzero.order32953.domain.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 公司主数据 管理 API
 *
 * @author 32953 2021-08-12 20:42:40
 */
@Api(tags = SwaggerTags.COMPANY)
@Slf4j
@RestController("companyController.v1")
@RequestMapping("/v1/{organizationId}/companys")
public class CompanyController extends BaseController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @ApiOperation(value = "公司主数据列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping
    public ResponseEntity<Page<Company>> list(@PathVariable("organizationId") Long organizationId,
                                              Company company,
                                              @ApiIgnore @SortDefault(value = Company.FIELD_COMPANY_ID,
                                                      direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<Company> list = companyService.list(organizationId, company, pageRequest);
        return Results.success(list);
    }

    @ApiOperation(value = "公司主数据明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{companyId}")
    public ResponseEntity<Company> detail(@PathVariable("organizationId") Long organizationId,
                                          @PathVariable Long companyId) {
        Company company = companyService.detail(organizationId, companyId);
        return Results.success(company);
    }

    @ApiOperation(value = "公司主数据模糊查询，根据公司编号和公司名称")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/lov")
    public ResponseEntity<Page<Company>> listLike(@PathVariable("organizationId") Long organizationId,
                                                  Company company,
                                                  @ApiIgnore @SortDefault(value = Company.FIELD_COMPANY_ID,
                                                          direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<Company> list = companyService.listLike(organizationId, company, pageRequest);
        return Results.success(list);
    }

    @ApiOperation(value = "创建公司主数据")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<Company> create(@PathVariable("organizationId") Long organizationId,
                                          @RequestBody Company company) {
        companyService.create(organizationId, company);
        return Results.success(company);
    }

    @ApiOperation(value = "修改公司主数据")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping
    public ResponseEntity<Company> update(@PathVariable("organizationId") Long organizationId,
                                          @RequestBody Company company) {
        companyService.update(organizationId, company);
        return Results.success(company);
    }

    @ApiOperation(value = "删除公司主数据")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody Company company) {
        companyService.remove(company);
        return Results.success();
    }

}
