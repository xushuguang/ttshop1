package com.xsg.ttshop.service;

import com.xsg.ttshop.common.dto.Order;
import com.xsg.ttshop.common.dto.Page;
import com.xsg.ttshop.common.dto.Result;
import com.xsg.ttshop.pojo.po.TbItemParam;
import com.xsg.ttshop.pojo.vo.TbItemParamCustom;

import java.util.List;

/**
 * User: 57140
 * Date: 2017/11/25
 * Time: 11:55
 * Version:V1.0
 */
public interface ItemParamService {
    Result<TbItemParamCustom> listItemParams(Page page, Order order);

    int saveItemParam(long cid, String paramData);

    int delItemParamsBatch(List<Long> ids);

    TbItemParam getByCid(Long cid);
}
