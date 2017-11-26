package com.xsg.ttshop.dao;

import com.xsg.ttshop.common.dto.Order;
import com.xsg.ttshop.common.dto.Page;
import com.xsg.ttshop.pojo.vo.TbItemParamCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User: DHC
 * Date: 2017/11/20
 * Time: 18:03
 * Version:V1.0
 */
public interface TbItemParamCustomMapper {
    /**
     * 符合条件的总记录数
     * @return
     */
    long countItemParams();

    /**
     * 指定页码的记录集合
     * @param page
     * @return
     */
    List<TbItemParamCustom> listItemParams(@Param("page") Page page, @Param("order") Order order);
}
