package com.xsg.ttshop.service;

import com.xsg.ttshop.pojo.po.TbContent;

import java.util.List;

/**
 * User: 57140
 * Date: 2017/12/4
 * Time: 19:29
 * Version:V1.0
 */
public interface ContentService {
    List<TbContent> listContentByCid(Long cid);
}
