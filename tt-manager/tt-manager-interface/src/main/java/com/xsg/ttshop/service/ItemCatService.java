package com.xsg.ttshop.service;

import com.xsg.ttshop.pojo.vo.TreeNode;

import java.util.List;

/**
 * User: 57140
 * Date: 2017/11/23
 * Time: 15:04
 * Version:V1.0
 */
public interface ItemCatService {
    public List<TreeNode> listItemCats(Long parentId);
}
