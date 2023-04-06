package org.hzero.order32953.app.service.impl;

import io.choerodon.mybatis.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.hzero.core.base.BaseAppService;

import org.hzero.mybatis.domian.Condition;
import org.hzero.mybatis.helper.SecurityTokenHelper;

import org.hzero.mybatis.util.Sqls;
import org.hzero.order32953.app.service.ItemService;
import org.hzero.order32953.domain.entity.Item;
import org.hzero.order32953.domain.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

/**
 * 物料主数据应用服务默认实现
 *
 * @author 32953 2021-08-12 20:42:40
 */
@Service
public class ItemServiceImpl extends BaseAppService implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Page<Item> list(Long tenantId, Item item, PageRequest pageRequest) {
        return itemRepository.pageAndSort(pageRequest, item);
    }

    /**
     * 这里是 Mybatis 增强组件中添加模糊查询的简单使用。
     * 通过构建好一个 SQL 客制化类，添加好需要模糊查询的属性和值，再将这个 SQL 类用来构建查询条件。
     * <p/>
     * 这样，不写 SQL 就能完成指定字段的模糊查询
     *
     * @param tenantId    租户ID
     * @param item        物料主数据
     * @param pageRequest 分页
     * @return 返回分页好的查询结果
     */
    @Override
    public Page<Item> listLike(Long tenantId, Item item, PageRequest pageRequest) {
        return PageHelper.doPageAndSort(pageRequest, () -> {
                    // 拿到sql客制化
                    Sqls custom = Sqls.custom();
                    // 取出传入的编号和名字
                    String itemCode = item.getItemCode();
                    String itemDescription = item.getItemDescription();
                    // 判断是否为空值，空值则不添加Like查询条件
                    if (!StringUtils.isEmpty(itemCode)) {
                        custom.andLike("itemCode", itemCode);
                    }
                    if (!StringUtils.isEmpty(itemDescription)) {
                        custom.andLike("itemDescription", itemDescription);
                    }
                    // 根据条件查询物料
                    return itemRepository.selectByCondition(
                            Condition.builder(Item.class).andWhere(custom).build()
                    );
                }
        );
    }

    @Override
    public Item detail(Long tenantId, Long itemId) {
        return itemRepository.selectByPrimaryKey(itemId);
    }

    @Override
    public Item create(Long tenantId, Item item) {
        validObject(item);
        itemRepository.insertSelective(item);
        return item;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Item update(Long tenantId, Item item) {
        SecurityTokenHelper.validToken(item);
        itemRepository.updateByPrimaryKeySelective(item);
        return item;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Item item) {
        SecurityTokenHelper.validToken(item);
        itemRepository.deleteByPrimaryKey(item);
    }
}
