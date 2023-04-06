package org.hzero.order32953.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;

import io.choerodon.mybatis.domain.AuditDomain;
import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 客户主数据
 *
 * @author 32953 2021-08-12 20:42:40
 */
@ApiModel("客户主数据")
@VersionAudit
@ModifyAudit
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Table(name = "hodr_customer")
public class Customer extends AuditDomain {

    public static final String FIELD_CUSTOMER_ID = "customerId";
    public static final String FIELD_CUSTOMER_NUMBER = "customerNumber";
    public static final String FIELD_CUSTOMER_NAME = "customerName";
    public static final String FIELD_ENABLED_FLAG = "enabledFlag";

//
// 业务方法(按public protected private顺序排列)
// ------------------------------------------------------------------------------

//
// 数据库字段
// ------------------------------------------------------------------------------


    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerNumber='" + customerNumber + '\'' +
                ", customerName='" + customerName + '\'' +
                ", enabledFlag=" + enabledFlag +
                '}';
    }

    @ApiModelProperty("客户ID(主键)")
    @Id
    @GeneratedValue
    private Long customerId;
    @ApiModelProperty(value = "客户编号", required = true)
    @NotBlank
    private String customerNumber;
    @ApiModelProperty(value = "客户名称", required = true)
    @NotBlank
    private String customerName;
    @ApiModelProperty(value = "启用标识", required = true)
    @NotNull
    private Integer enabledFlag;

//
// 非数据库字段
// ------------------------------------------------------------------------------

//
// getter/setter
// ------------------------------------------------------------------------------

    /**
     * @return 客户ID(主键)
     */
    public Long getCustomerId() {
        return customerId;
    }

    public Customer setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    /**
     * @return 客户编号
     */
    public String getCustomerNumber() {
        return customerNumber;
    }

    public Customer setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
        return this;
    }

    /**
     * @return 客户名称
     */
    public String getCustomerName() {
        return customerName;
    }

    public Customer setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    /**
     * @return 启用标识
     */
    public Integer getEnabledFlag() {
        return enabledFlag;
    }

    public Customer setEnabledFlag(Integer enabledFlag) {
        this.enabledFlag = enabledFlag;
        return this;
    }
}
