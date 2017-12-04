package com.xsg.ttshop.service.impl;

import com.xsg.ttshop.common.dto.Order;
import com.xsg.ttshop.common.dto.Page;
import com.xsg.ttshop.common.dto.Result;
import com.xsg.ttshop.dao.TbItemParamCustomMapper;
import com.xsg.ttshop.dao.TbItemParamMapper;
import com.xsg.ttshop.pojo.po.TbItemParam;
import com.xsg.ttshop.pojo.po.TbItemParamExample;
import com.xsg.ttshop.pojo.vo.TbItemCustom;
import com.xsg.ttshop.pojo.vo.TbItemParamCustom;
import com.xsg.ttshop.service.ItemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * User: 57140
 * Date: 2017/11/25
 * Time: 11:57
 * Version:V1.0
 */
@Service
public class ItemParamServiceImpl implements ItemParamService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbItemParamMapper itemParamDao;
    @Autowired
    private TbItemParamCustomMapper itemParamCustomDao;
    @Override
    public Result<TbItemParamCustom> listItemParams(Page page, Order order) {
        Result<TbItemParamCustom> result = null;
        try {
            //1 先查总记录数 int--Long
            long total = itemParamCustomDao.countItemParams();
            //2 查询指定页码的记录集合
            List<TbItemParamCustom> list = itemParamCustomDao.listItemParams(page, order);
            //3 存放result中
            result = new Result<TbItemParamCustom>();
            result.setTotal(total);
            result.setRows(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }

    @Transactional
    @Override
    public int saveItemParam(long cid, String paramData) {
        int result = 0;
        try {
            TbItemParam itemParam = new TbItemParam();
            itemParam.setItemCatId(cid);
            itemParam.setParamData(paramData);
            itemParam.setCreated(new Date());
            itemParam.setUpdated(new Date());
            result = itemParamDao.insert(itemParam);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delItemParamsBatch(List<Long> ids) {
        int result = 0;
        try {
            TbItemParamExample paramExample = new TbItemParamExample();
            TbItemParamExample.Criteria criteria = paramExample.createCriteria();
            criteria.andIdIn(ids);
            result = itemParamDao.deleteByExample(paramExample);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public TbItemParam getByCid(Long cid) {
        TbItemParam itemParam = null;
        try {
            TbItemParamExample example = new TbItemParamExample();
            TbItemParamExample.Criteria criteria = example.createCriteria();
            criteria.andItemCatIdEqualTo(cid);
            List<TbItemParam> list = itemParamDao.selectByExampleWithBLOBs(example);
            if (list != null && list.size()>0){
                itemParam = list.get(0);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return itemParam;
    }
}
