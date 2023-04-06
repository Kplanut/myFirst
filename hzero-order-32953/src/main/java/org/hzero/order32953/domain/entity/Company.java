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
 * 公司主数据
 *
 * @author 32953 2021-08-12 20:42:40
 */
@ApiModel("公司主数据")
@VersionAudit
@ModifyAudit
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Table(name = "hodr_company")
public class Company extends AuditDomain {

    public static final String FIELD_COMPANY_ID = "companyId";
    public static final String FIELD_COMPANY_NUMBER = "companyNumber";
    public static final String FIELD_COMPANY_NAME = "companyName";
    public static final String FIELD_ENABLED_FLAG = "enabledFlag";

//
// 业务方法(按public protected private顺序排列)
// ------------------------------------------------------------------------------

//
// 数据库字段
// ------------------------------------------------------------------------------


    @ApiModelProperty("公司ID(主键)")
    @Id
    @GeneratedValue
    private Long companyId;
    @ApiModelProperty(value = "公司编号", required = true)
    @NotBlank
    private String companyNumber;
    @ApiModelProperty(value = "公司名称", required = true)
    @NotBlank
    private String companyName;
    @ApiModelProperty(value = "启用标识", required = true)
    @NotNull
    private Integer enabledFlag;

//
// 非数据库字段
// ------------------------------------------------------------------------------

//
// getter/setter
// ------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", companyNumber='" + companyNumber + '\'' +
                ", companyName='" + companyName + '\'' +
                ", enabledFlag=" + enabledFlag +
                '}';
    }

    /**
     * @return 公司ID(主键)
     */
    public Long getCompanyId() {
        return companyId;
    }

    public Company setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    /**
     * @return 公司编号
     */
    public String getCompanyNumber() {
        return companyNumber;
    }

    public Company setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
        return this;
    }

    /**
     * @return 公司名称
     */
    public String getCompanyName() {
        return companyName;
    }

    public Company setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    /**
     * @return 启用标识
     */
    public Integer getEnabledFlag() {
        return enabledFlag;
    }

    public Company setEnabledFlag(Integer enabledFlag) {
        this.enabledFlag = enabledFlag;
        return this;
    }
}
