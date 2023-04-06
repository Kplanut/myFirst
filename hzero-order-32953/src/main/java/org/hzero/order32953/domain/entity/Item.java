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

import java.util.Date;

/**
 * 物料主数据
 *
 * @author 32953 2021-08-12 20:42:40
 */
@ApiModel("物料主数据")
@VersionAudit
@ModifyAudit
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Table(name = "hodr_item")
public class Item extends AuditDomain {

    public static final String FIELD_ITEM_ID = "itemId";
    public static final String FIELD_ITEM_CODE = "itemCode";
    public static final String FIELD_ITEM_UOM = "itemUom";
    public static final String FIELD_ITEM_DESCRIPTION = "itemDescription";
    public static final String FIELD_SALEABLE_FLAG = "saleableFlag";
    public static final String FIELD_START_ACTIVE_DATE = "startActiveDate";
    public static final String FIELD_END_ACTIVE_DATE = "endActiveDate";
    public static final String FIELD_ENABLED_FLAG = "enabledFlag";

//
// 业务方法(按public protected private顺序排列)
// ------------------------------------------------------------------------------

//
// 数据库字段
// ------------------------------------------------------------------------------


    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemCode='" + itemCode + '\'' +
                ", itemUom='" + itemUom + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", saleableFlag=" + saleableFlag +
                ", startActiveDate=" + startActiveDate +
                ", endActiveDate=" + endActiveDate +
                ", enabledFlag=" + enabledFlag +
                '}';
    }

    @ApiModelProperty("物料ID(主键)")
    @Id
    @GeneratedValue
    private Long itemId;
    @ApiModelProperty(value = "物料编码", required = true)
    @NotBlank
    private String itemCode;
    @ApiModelProperty(value = "物料单位", required = true)
    @NotBlank
    private String itemUom;
    @ApiModelProperty(value = "物料描述", required = true)
    @NotBlank
    private String itemDescription;
    @ApiModelProperty(value = "可销售标识", required = true)
    @NotNull
    private Integer saleableFlag;
    @ApiModelProperty(value = "生效起始时间")
    private Date startActiveDate;
    @ApiModelProperty(value = "生效结束时间")
    private Date endActiveDate;
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
     * @return 物料ID(主键)
     */
    public Long getItemId() {
        return itemId;
    }

    public Item setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    /**
     * @return 物料编码
     */
    public String getItemCode() {
        return itemCode;
    }

    public Item setItemCode(String itemCode) {
        this.itemCode = itemCode;
        return this;
    }

    /**
     * @return 物料单位
     */
    public String getItemUom() {
        return itemUom;
    }

    public Item setItemUom(String itemUom) {
        this.itemUom = itemUom;
        return this;
    }

    /**
     * @return 物料描述
     */
    public String getItemDescription() {
        return itemDescription;
    }

    public Item setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
        return this;
    }

    /**
     * @return 可销售标识
     */
    public Integer getSaleableFlag() {
        return saleableFlag;
    }

    public Item setSaleableFlag(Integer saleableFlag) {
        this.saleableFlag = saleableFlag;
        return this;
    }

    /**
     * @return 生效起始时间
     */
    public Date getStartActiveDate() {
        return startActiveDate;
    }

    public Item setStartActiveDate(Date startActiveDate) {
        this.startActiveDate = startActiveDate;
        return this;
    }

    /**
     * @return 生效结束时间
     */
    public Date getEndActiveDate() {
        return endActiveDate;
    }

    public Item setEndActiveDate(Date endActiveDate) {
        this.endActiveDate = endActiveDate;
        return this;
    }

    /**
     * @return 启用标识
     */
    public Integer getEnabledFlag() {
        return enabledFlag;
    }

    public Item setEnabledFlag(Integer enabledFlag) {
        this.enabledFlag = enabledFlag;
        return this;
    }
}
