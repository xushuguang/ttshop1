package com.xsg.ttshop.service.impl;

import com.xsg.ttshop.common.dto.Order;
import com.xsg.ttshop.common.dto.Page;
import com.xsg.ttshop.common.dto.Result;
import com.xsg.ttshop.dao.TbItemCustomMapper;
import com.xsg.ttshop.dao.TbItemMapper;
import com.xsg.ttshop.pojo.po.TbItem;
import com.xsg.ttshop.pojo.po.TbItemExample;
import com.xsg.ttshop.pojo.vo.TbItemCustom;
import com.xsg.ttshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: DHC
 * Date: 2017/11/17
 * Time: 15:27
 * Version:V1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemMapper itemDao;
    @Autowired
    private TbItemCustomMapper itemCustomDao;
    @Override
    public TbItem getById(Long itemId) {
        return itemDao.selectByPrimaryKey(itemId);
    }

    @Override
    public List<TbItem> listItems() {
        List<TbItem> tbItemList = null;
        try {
            //查询所有商品
            tbItemList = itemDao.selectByExample(null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return tbItemList;
    }

    @Override
    public Result<TbItemCustom> listItems(Page page, Order order) {
        Result<TbItemCustom> result = null;
        try {
            //1 先查总记录数 int--Long
            long total = itemCustomDao.countItems();
            //2 查询指定页码的记录集合
            List<TbItemCustom> list = itemCustomDao.listItems(page,order);
            //3 存放result中
            result = new Result<TbItemCustom>();
            result.setTotal(total);
            result.setRows(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int itemsBatch(List<Long> ids,byte code) {
        int i = 0;
        try {
            TbItem record = new TbItem();
            record.setStatus(code);
            //创建模板
            TbItemExample example = new TbItemExample();
            TbItemExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids);
            //执行更新操作
            i = itemDao.updateByExampleSelective(record, example);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return i;
    }
}
