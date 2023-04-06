package org.hzero.order32953.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;

import io.choerodon.mybatis.domain.AuditDomain;

import java.math.BigDecimal;
import java.util.List;

import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 销售订单行信息
 *
 * @author 32953 2021-08-12 20:42:40
 */
@ApiModel("销售订单行信息")
@VersionAudit
@ModifyAudit
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Table(name = "hodr_so_line")
public class SoLine extends AuditDomain {

    public static final String FIELD_SO_LINE_ID = "soLineId";
    public static final String FIELD_SO_HEADER_ID = "soHeaderId";
    public static final String FIELD_LINE_NUMBER = "lineNumber";
    public static final String FIELD_ITEM_ID = "itemId";
    public static final String FIELD_ORDER_QUANTITY = "orderQuantity";
    public static final String FIELD_ORDER_QUANTITY_UOM = "orderQuantityUom";
    public static final String FIELD_UNIT_SELLING_PRICE = "unitSellingPrice";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_ADDITION1 = "addition1";
    public static final String FIELD_ADDITION2 = "addition2";
    public static final String FIELD_ADDITION3 = "addition3";
    public static final String FIELD_ADDITION4 = "addition4";
    public static final String FIELD_ADDITION5 = "addition5";

//
// 业务方法(按public protected private顺序排列)
// ------------------------------------------------------------------------------

//
// 数据库字段
// ------------------------------------------------------------------------------

    @ApiModelProperty("订单行ID(主键)")
    @Id
    @GeneratedValue
    private Long soLineId;
    @ApiModelProperty(value = "订单头ID", required = true)
    @NotNull
    private Long soHeaderId;
    @ApiModelProperty(value = "行号", required = true)
    @NotNull
    private Long lineNumber;
    @ApiModelProperty(value = "产品ID", required = true)
    @NotNull
    private Long itemId;
    @ApiModelProperty(value = "数量", required = true)
    @NotNull
    private BigDecimal orderQuantity;
    @ApiModelProperty(value = "产品单位", required = true)
    @NotBlank
    private String orderQuantityUom;
    @ApiModelProperty(value = "销售单价", required = true)
    @NotNull
    private BigDecimal unitSellingPrice;
    @ApiModelProperty(value = "备注")
    private String description;
    @ApiModelProperty(value = "附加信息1")
    private String addition1;
    @ApiModelProperty(value = "附加信息2")
    private String addition2;
    @ApiModelProperty(value = "附加信息3")
    private String addition3;
    @ApiModelProperty(value = "附加信息4")
    private String addition4;
    @ApiModelProperty(value = "附加信息5")
    private String addition5;

//
// 非数据库字段
// ------------------------------------------------------------------------------

    @Transient
    @ApiModelProperty(value = "物料")
    private Item item;
//
// getter/setter
// ------------------------------------------------------------------------------


    @Override
    public String toString() {
        return "SoLine{" +
                "soLineId=" + soLineId +
                ", soHeaderId=" + soHeaderId +
                ", lineNumber=" + lineNumber +
                ", itemId=" + itemId +
                ", orderQuantity=" + orderQuantity +
                ", orderQuantityUom='" + orderQuantityUom + '\'' +
                ", unitSellingPrice=" + unitSellingPrice +
                ", description='" + description + '\'' +
                ", addition1='" + addition1 + '\'' +
                ", addition2='" + addition2 + '\'' +
                ", addition3='" + addition3 + '\'' +
                ", addition4='" + addition4 + '\'' +
                ", addition5='" + addition5 + '\'' +
                ", item=" + item +
                '}';
    }

    /**
     * @return 物料
     */
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * @return 订单行ID(主键)
     */
    public Long getSoLineId() {
        return soLineId;
    }

    public SoLine setSoLineId(Long soLineId) {
        this.soLineId = soLineId;
        return this;
    }

    /**
     * @return 订单头ID
     */
    public Long getSoHeaderId() {
        return soHeaderId;
    }

    public SoLine setSoHeaderId(Long soHeaderId) {
        this.soHeaderId = soHeaderId;
        return this;
    }

    /**
     * @return 行号
     */
    public Long getLineNumber() {
        return lineNumber;
    }

    public SoLine setLineNumber(Long lineNumber) {
        this.lineNumber = lineNumber;
        return this;
    }

    /**
     * @return 产品ID
     */
    public Long getItemId() {
        return itemId;
    }

    public SoLine setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    /**
     * @return 数量
     */
    public BigDecimal getOrderQuantity() {
        return orderQuantity;
    }

    public SoLine setOrderQuantity(BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
        return this;
    }

    /**
     * @return 产品单位
     */
    public String getOrderQuantityUom() {
        return orderQuantityUom;
    }

    public SoLine setOrderQuantityUom(String orderQuantityUom) {
        this.orderQuantityUom = orderQuantityUom;
        return this;
    }

    /**
     * @return 销售单价
     */
    public BigDecimal getUnitSellingPrice() {
        return unitSellingPrice;
    }

    public SoLine setUnitSellingPrice(BigDecimal unitSellingPrice) {
        this.unitSellingPrice = unitSellingPrice;
        return this;
    }

    /**
     * @return 备注
     */
    public String getDescription() {
        return description;
    }

    public SoLine setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * @return 附加信息1
     */
    public String getAddition1() {
        return addition1;
    }

    public SoLine setAddition1(String addition1) {
        this.addition1 = addition1;
        return this;
    }

    /**
     * @return 附加信息2
     */
    public String getAddition2() {
        return addition2;
    }

    public SoLine setAddition2(String addition2) {
        this.addition2 = addition2;
        return this;
    }

    /**
     * @return 附加信息3
     */
    public String getAddition3() {
        return addition3;
    }

    public SoLine setAddition3(String addition3) {
        this.addition3 = addition3;
        return this;
    }

    /**
     * @return 附加信息4
     */
    public String getAddition4() {
        return addition4;
    }

    public SoLine setAddition4(String addition4) {
        this.addition4 = addition4;
        return this;
    }

    /**
     * @return 附加信息5
     */
    public String getAddition5() {
        return addition5;
    }

    public SoLine setAddition5(String addition5) {
        this.addition5 = addition5;
        return this;
    }
}
