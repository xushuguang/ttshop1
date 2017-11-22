package com.xsg.ttshop.service;

import com.xsg.ttshop.common.dto.Page;
import com.xsg.ttshop.common.dto.Result;
import com.xsg.ttshop.pojo.po.TbItem;
import com.xsg.ttshop.pojo.vo.TbItemCustom;

import java.util.List;

/**
 * User: DHC
 * Date: 2017/11/17
 * Time: 15:26
 * Version:V1.0
 */
public interface ItemService {
    /**
     * 通过商品主键查询单个商品
     * @param itemId
     * @return
     */
    TbItem getById(Long itemId);

    /**
     * 不带分页的查询所有商品
     * @return
     */
    List<TbItem> listItems();

    /**
     * 带分页的查询所有商品
     * @return
     */
    Result<TbItemCustom> listItems(Page page);

    /**
     * 批量修改商品的状态
     * @param ids
     * @return
     */
    int itemsBatch(List<Long> ids);
}
