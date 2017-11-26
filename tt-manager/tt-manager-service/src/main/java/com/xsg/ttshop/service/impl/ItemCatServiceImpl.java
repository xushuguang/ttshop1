package com.xsg.ttshop.service.impl;

import com.xsg.ttshop.dao.TbItemCatMapper;
import com.xsg.ttshop.pojo.po.TbItemCat;
import com.xsg.ttshop.pojo.po.TbItemCatExample;
import com.xsg.ttshop.pojo.vo.TreeNode;
import com.xsg.ttshop.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: 57140
 * Date: 2017/11/23
 * Time: 15:05
 * Version:V1.0
 */
@Service
public class ItemCatServiceImpl implements ItemCatService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbItemCatMapper itemCatDao;
    @Override
    public List<TreeNode> listItemCats(Long parentId) {
        List<TreeNode> resultList = null;
        try {
            //创建查询模板
            TbItemCatExample example = new TbItemCatExample();
            TbItemCatExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(parentId);
            //执行查询
            List<TbItemCat> list = itemCatDao.selectByExample(example);
            resultList = new ArrayList<TreeNode>();
            //遍历原有集合
            for (TbItemCat itemCat:list) {
               TreeNode node = new TreeNode();
               node.setId(itemCat.getId());
               node.setText(itemCat.getName());
               node.setState(itemCat.getIsParent()?"closed":"open");
               resultList.add(node);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return  resultList;
    }
}
