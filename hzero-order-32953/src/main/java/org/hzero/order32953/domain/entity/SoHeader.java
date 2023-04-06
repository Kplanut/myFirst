package org.hzero.order32953.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;

import io.choerodon.mybatis.domain.AuditDomain;
import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hzero.export.annotation.ExcelColumn;
import org.hzero.export.annotation.ExcelSheet;

import java.util.Date;
import java.util.List;

/**
 * 销售订单头信息
 *
 * @author 32953 2021-08-12 20:42:40
 */
@ApiModel("销售订单头信息")
@VersionAudit
@ModifyAudit
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@ExcelSheet(zh = "订单详情", en = "order")
@Table(name = "hodr_so_header")
public class SoHeader extends AuditDomain {

    public static final String FIELD_SO_HEADER_ID = "soHeaderId";
    public static final String FIELD_ORDER_NUMBER = "orderNumber";
    public static final String FIELD_COMPANY_ID = "companyId";
    public static final String FIELD_ORDER_DATE = "orderDate";
    public static final String FIELD_ORDER_STATUS = "orderStatus";
    public static final String FIELD_CUSTOMER_ID = "customerId";

//
// 业务方法(按public protected private顺序排列)
// ------------------------------------------------------------------------------

//
// 数据库字段
// ------------------------------------------------------------------------------


    @ApiModelProperty("订单头ID(主键)")
    @Id
    @GeneratedValue
    private Long soHeaderId;

    @ExcelColumn(zh = "订单编号", en = "orderNumber")
    @ApiModelProperty(value = "订单编号", required = true)
    @NotBlank
    private String orderNumber;

    @ApiModelProperty(value = "公司ID", required = true)
    @NotNull
    private Long companyId;

    @ExcelColumn(zh = "订单日期", en = "orderDate")
    @ApiModelProperty(value = "订单日期", required = true)
    @NotNull
    private Date orderDate;

    @ExcelColumn(zh = "订单状态", en = "orderStatus", lovCode = "HZERO.32953.ORDER.STATUS")
    @ApiModelProperty(value = "订单状态", required = true)
    @NotBlank
    private String orderStatus;


    @ApiModelProperty(value = "客户ID", required = true)
    @NotNull
    private Long customerId;

//
// 非数据库字段
// ------------------------------------------------------------------------------

    @Transient
    @ApiModelProperty(value = "订单行")
    private List<SoLine> soLines;

    @Transient
    @ApiModelProperty(value = "公司")
    private Company company;

    @Transient
    @ApiModelProperty(value = "客户")
    private Customer customer;


    @ExcelColumn(zh = "订单总价", en = "totalPrice")
    @Transient
    @ApiModelProperty(value = "订单总价")
    private Long totalPrice;
//
// getter/setter
// ------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "SoHeader{" +
                "soHeaderId=" + soHeaderId +
                ", orderNumber='" + orderNumber + '\'' +
                ", companyId=" + companyId +
                ", orderDate=" + orderDate +
                ", orderStatus='" + orderStatus + '\'' +
                ", customerId=" + customerId +
                ", soLines=" + soLines +
                ", company=" + company +
                ", customer=" + customer +
                ", totalPrice=" + totalPrice +
                '}';
    }

    /**
     * @return 订单总价
     */
    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return 公司
     */
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * @return 客户
     */
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return 订单行
     */
    public List<SoLine> getSoLines() {
        return soLines;
    }

    public SoHeader setSoLines(List<SoLine> soLines) {
        this.soLines = soLines;
        return this;
    }

    /**
     * @return 订单头ID(主键)
     */
    public Long getSoHeaderId() {
        return soHeaderId;
    }

    public SoHeader setSoHeaderId(Long soHeaderId) {
        this.soHeaderId = soHeaderId;
        return this;
    }

    /**
     * @return 订单编号
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    public SoHeader setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    /**
     * @return 公司ID
     */
    public Long getCompanyId() {
        return companyId;
    }

    public SoHeader setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    /**
     * @return 订单日期
     */
    public Date getOrderDate() {
        return orderDate;
    }

    public SoHeader setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    /**
     * @return 订单状态
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    public SoHeader setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    /**
     * @return 客户ID
     */
    public Long getCustomerId() {
        return customerId;
    }

    public SoHeader setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }
}
