package org.hzero.order32953.api.controller.v1;

import io.choerodon.core.domain.Page;
import io.choerodon.core.exception.CommonException;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.core.oauth.DetailsHelper;
import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hzero.boot.message.MessageClient;
import org.hzero.boot.message.entity.Attachment;
import org.hzero.boot.message.entity.Message;
import org.hzero.boot.message.entity.Receiver;
import org.hzero.boot.platform.code.builder.CodeRuleBuilder;
import org.hzero.boot.platform.code.constant.CodeConstants;
import org.hzero.boot.workflow.WorkflowClient;
import org.hzero.boot.workflow.dto.RunInstance;
import org.hzero.core.base.BaseController;
import org.hzero.core.util.Results;
import org.hzero.export.annotation.ExcelExport;
import org.hzero.export.vo.ExportParam;
import org.hzero.order32953.app.service.SoHeaderService;
import org.hzero.order32953.config.SwaggerTags;
import org.hzero.order32953.domain.entity.SoHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 销售订单头信息 管理 API
 *
 * @author 32953 2021-08-12 20:42:40
 */

@Api(tags = SwaggerTags.SO_HEADER)
@Slf4j
@RestController("soHeaderController.v1")
@RequestMapping("/v1/{organizationId}/so-headers")
public class SoHeaderController extends BaseController {

    private final SoHeaderService soHeaderService;
    private final CodeRuleBuilder codeRuleBuilder;
    private final MessageClient messageClient;
    private final WorkflowClient workflowClient;

    @Autowired
    public SoHeaderController(SoHeaderService soHeaderService, CodeRuleBuilder codeRuleBuilder, MessageClient messageClient, WorkflowClient workflowClient) {
        this.soHeaderService = soHeaderService;
        this.codeRuleBuilder = codeRuleBuilder;
        this.messageClient = messageClient;
        this.workflowClient = workflowClient;
    }

    @ApiOperation(value = "新增订单，同时保存头和行")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping("/save")
    public ResponseEntity<?> saveWithLine(@PathVariable("organizationId") Long organizationId, @RequestBody SoHeader soHeader) {
        // 日期不能早于2019
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(soHeader.getCreationDate());
        int i = calendar.get(Calendar.YEAR);
        if (i < 2019) {
            throw new CommonException("订单日期不能早于2019");
        }
        // 使用规则生成的订单编码
        String ruleCode = "HZERO.32953.ORDER.NUMBER";
        String levelValue = "GLOBAL";
        String code = codeRuleBuilder.generateCode(CodeConstants.Level.TENANT, organizationId, ruleCode, CodeConstants.CodeRuleLevelCode.GLOBAL, levelValue, null);
        soHeader.setOrderNumber(code);
        // 设置默认状态
        soHeader.setOrderStatus("NEW");
        // 保存订单头和订单行
        soHeaderService.save(soHeader);
        return Results.success("保存成功");
    }

    @ApiOperation(value = "修改订单，同时修改头和行")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping("/save")
    public ResponseEntity<?> updateWithLine(@PathVariable("organizationId") Long organizationId, @RequestBody SoHeader soHeader) {
        validStatus(organizationId, soHeader);
        // 订单修改保存
        soHeaderService.updateWithLine(soHeader);
        return Results.success("修改成功");
    }

    @ApiOperation(value = "提交订单")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping("/submit")
    public ResponseEntity<?> submit(@PathVariable("organizationId") Long organizationId, @RequestBody SoHeader soHeader, String orderStatus) {
        // 初始化发起工作流时的参数
        String flowKey = "FLOW1427591323147603969";
        String starter = "sale";
//        String starter = DetailsHelper.getUserDetails().getUsername();
//        Long userId = DetailsHelper.getUserDetails().getUserId();
//        if (!Objects.equals(userId, soHeader.getCreatedBy())) {
//            throw new CommonException("当前用户不是流程发起人，无法提交");
//        }
        Long soHeaderId = soHeader.getSoHeaderId();
        String businessKey = "client_submit_" + soHeaderId;
        // 设置工作流中的流程变量
        Map<String, Object> variableMap = new HashMap<>();
        variableMap.put("soHeaderId", soHeaderId);
        variableMap.put("orderStatus", orderStatus);
        // 运行工作流
        RunInstance runInstance = workflowClient.startInstanceByFlowKey(organizationId, flowKey, businessKey, starter, variableMap);
        // 修改状态信息
        soHeaderService.updateOrderStatus(soHeaderId, orderStatus, null);
        log.info("工作流正在运行 {}", runInstance);
        return Results.success("工作流已启动");
    }

    @ApiOperation(value = "审批订单")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping("/approve")
    public ResponseEntity<?> approve(@PathVariable("organizationId") Long organizationId, String orderStatus, Long soHeaderId, String nodeApproveResult) {
        // 判断节点审批结果
        if ("APPROVED".equals(nodeApproveResult)) {
            orderStatus = "APPROVED";
        } else if ("REJECTED".equals(nodeApproveResult)) {
            orderStatus = "REJECTED";
        }
        // 修改订单状态
        soHeaderService.updateOrderStatus(soHeaderId, orderStatus, null);
        // 初始化发送邮件的参数
        String serverCode = "DEMO_MAIL";
        String messageTemplateCode = "HODR.32953.MESSAGE";
        List<Receiver> receiverList = new ArrayList<>();
        Receiver receiver = new Receiver();
        receiver.setEmail("1556108970@qq.com");
        receiverList.add(receiver);
        // 写入邮件中的变量
        Map<String, String> args = new HashMap<>();
        args.put("orderStatus", orderStatus);
        // 发送邮件
        Message message = messageClient.sendEmail(organizationId, serverCode, messageTemplateCode, receiverList, args, (Attachment) null);
        log.info("正在发送邮件===={}", message);
        return Results.success("经理已经审批，邮件可查看审批状态");
    }

    @ApiOperation(value = "整单删除")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping("/dto")
    public ResponseEntity<?> deleteDto(@PathVariable("organizationId") Long organizationId, @RequestBody SoHeader soHeader) {
        validStatus(organizationId, soHeader);
        soHeaderService.delete(soHeader);
        return Results.success("整单删除成功");
    }

    @ApiOperation(value = "销售订单头信息列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @ExcelExport(SoHeader.class)
    @GetMapping
    public ResponseEntity<Page<SoHeader>> list(@PathVariable("organizationId") Long organizationId, SoHeader soHeader, @ApiIgnore @SortDefault(value = SoHeader.FIELD_SO_HEADER_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest, ExportParam exportParam, HttpServletResponse response) {
        Page<SoHeader> list = soHeaderService.list(organizationId, soHeader, pageRequest);
        return Results.success(list);
    }

    @ApiOperation(value = "销售订单头信息明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{soHeaderId}")
    public ResponseEntity<SoHeader> detail(@PathVariable("organizationId") Long organizationId, @PathVariable Long soHeaderId) {
        return Results.success(soHeaderService.getById(soHeaderId));
    }

    @ApiOperation(value = "创建销售订单头信息")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<SoHeader> create(@PathVariable("organizationId") Long organizationId, @RequestBody SoHeader soHeader) {
        soHeaderService.create(organizationId, soHeader);
        return Results.success(soHeader);
    }

    @ApiOperation(value = "修改销售订单头信息")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping
    public ResponseEntity<SoHeader> update(@PathVariable("organizationId") Long organizationId, @RequestBody SoHeader soHeader) {
        soHeaderService.update(organizationId, soHeader);
        return Results.success(soHeader);
    }

    @ApiOperation(value = "删除销售订单头信息")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping
    public ResponseEntity<?> remove(@PathVariable("organizationId") Long organizationId, @RequestBody List<SoHeader> soHeader) {
        soHeaderService.remove(soHeader);
        return Results.success();
    }

    /**
     * 校验传入订单的状态和操作人
     *
     * @param organizationId 租户ID
     * @param soHeader 被检验的订单
     */
    private void validStatus(Long organizationId, SoHeader soHeader) {
        // 检验订单状态
        SoHeader detail = soHeaderService.detail(organizationId, soHeader.getSoHeaderId());
        String orderStatus = detail.getOrderStatus();
        if (!"NEW".equals(orderStatus) && !"REJECTED".equals(orderStatus)) {
            throw new CommonException("当前订单状态不允许操作");
        }
        // 检验当前用户
        Long userId = DetailsHelper.getUserDetails().getUserId();
        if (!Objects.equals(userId, detail.getCreatedBy())) {
            throw new CommonException("失败，当前用户不是订单创建用户");
        }
    }

}
