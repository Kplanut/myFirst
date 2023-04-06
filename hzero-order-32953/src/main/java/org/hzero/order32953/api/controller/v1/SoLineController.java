package org.hzero.order32953.api.controller.v1;

import io.swagger.annotations.Api;
import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;

import org.hzero.order32953.app.service.SoLineService;
import org.hzero.order32953.config.SwaggerTags;
import org.hzero.order32953.domain.entity.SoLine;
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

import java.util.List;

/**
 * 销售订单行信息 管理 API
 *
 * @author 32953 2021-08-12 20:42:40
 */
@Api(tags = SwaggerTags.SO_LINE)
@RestController("soLineController.v1")
@RequestMapping("/v1/{organizationId}/so-lines")
public class SoLineController extends BaseController {

    private final SoLineService soLineService;

    @Autowired
    public SoLineController(SoLineService soLineService) {
        this.soLineService = soLineService;
    }

    @ApiOperation(value = "销售订单行信息列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping
    public ResponseEntity<Page<SoLine>> list(@PathVariable("organizationId") Long organizationId, SoLine soLine, @ApiIgnore @SortDefault(value = SoLine.FIELD_LINE_NUMBER,
            direction = Sort.Direction.ASC) PageRequest pageRequest) {
        Page<SoLine> list = soLineService.listLineWithItem(soLine, pageRequest);
        return Results.success(list);
    }

    @ApiOperation(value = "销售订单行信息列表，根据头id查询")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{soHeaderId}")
    public ResponseEntity<Page<SoLine>> listBySoHeaderId(@PathVariable("organizationId") Long organizationId,
                                                         @PathVariable("soHeaderId") Long soHeaderId,
                                                         @ApiIgnore @SortDefault(value = SoLine.FIELD_LINE_NUMBER,
                                                                 direction = Sort.Direction.ASC) PageRequest pageRequest) {
        Page<SoLine> list = soLineService.listLineWithItem(new SoLine().setSoHeaderId(soHeaderId), pageRequest);
        return Results.success(list);
    }

    @ApiOperation(value = "创建销售订单行信息")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<List<SoLine>> create(@PathVariable("organizationId") Long organizationId, @RequestBody List<SoLine> soLine) {
        soLineService.create(organizationId, soLine);
        return Results.success(soLine);
    }

    @ApiOperation(value = "修改销售订单行信息")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping
    public ResponseEntity<List<SoLine>> update(@PathVariable("organizationId") Long organizationId, @RequestBody List<SoLine> soLine) {
        soLineService.update(organizationId, soLine);
        return Results.success(soLine);
    }

    @ApiOperation(value = "删除销售订单行信息")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody List<SoLine> soLine) {
        soLineService.remove(soLine);
        return Results.success();
    }

}
