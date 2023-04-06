package org.hzero.order32953.app.service;

import org.hzero.order32953.domain.entity.Item;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

/**
 * 物料主数据应用服务
 *
 * @author 32953 2021-08-12 20:42:40
 */
public interface ItemService {

    /**
     * 物料主数据查询参数
     *
     * @param tenantId    租户ID
     * @param item        物料主数据
     * @param pageRequest 分页
     * @return 物料主数据列表
     */
    Page<Item> list(Long tenantId, Item item, PageRequest pageRequest);

    /**
     * 物料主数据模糊查询，根据物料编码和物料名称
     *
     * @param tenantId    租户ID
     * @param item        物料主数据
     * @param pageRequest 分页
     * @return 物料主数据列表
     */
    Page<Item> listLike(Long tenantId, Item item, PageRequest pageRequest);


    /**
     * 物料主数据详情
     *
     * @param tenantId 租户ID
     * @param itemId   主键
     * @return 物料主数据列表
     */
    Item detail(Long tenantId, Long itemId);

    /**
     * 创建物料主数据
     *
     * @param tenantId 租户ID
     * @param item     物料主数据
     * @return 物料主数据
     */
    Item create(Long tenantId, Item item);

    /**
     * 更新物料主数据
     *
     * @param tenantId 租户ID
     * @param item     物料主数据
     * @return 物料主数据
     */
    Item update(Long tenantId, Item item);

    /**
     * 删除物料主数据
     *
     * @param item 物料主数据
     */
    void remove(Item item);
}
