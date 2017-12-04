package com.xsg.ttshop.web;

import com.xsg.ttshop.common.dto.Order;
import com.xsg.ttshop.common.dto.Page;
import com.xsg.ttshop.common.dto.Result;
import com.xsg.ttshop.pojo.po.TbItemParam;
import com.xsg.ttshop.pojo.vo.TbItemParamCustom;
import com.xsg.ttshop.service.ItemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: 57140
 * Date: 2017/11/25
 * Time: 11:45
 * Version:V1.0
 */
@Controller
public class ItemParamAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ItemParamService itemParamService;
    @ResponseBody
    @RequestMapping(value = "/itemParam/findByPage", method= RequestMethod.GET)
    public Result<TbItemParamCustom> listItemParams(Page page, Order order){
        Result<TbItemParamCustom> result = null;
        try {
            result = itemParamService.listItemParams(page,order);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "/itemParam/save/{cid}", method= RequestMethod.POST)
    public int saveItemParam(@PathVariable("cid")long cid,String paramData){
        int result = 0;
        try {
            result = itemParamService.saveItemParam(cid,paramData);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "/itemParam/delete", method= RequestMethod.POST)
    public int delItemParamsBatch(@RequestParam("ids[]") List<Long> ids){
        int result = 0;
        try {
            result = itemParamService.delItemParamsBatch(ids);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "/itemParam/{cid}",method = RequestMethod.GET)
    public TbItemParam getByCid(@PathVariable("cid") Long cid){
        TbItemParam itemParam = null;
       try {
           itemParam = itemParamService.getByCid(cid);
       }catch (Exception e){
           logger.error(e.getMessage(),e);
       }
        return itemParam;
    }

}
