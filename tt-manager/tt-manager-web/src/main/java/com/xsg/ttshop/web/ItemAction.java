package com.xsg.ttshop.web;

import com.xsg.ttshop.common.dto.Order;
import com.xsg.ttshop.common.dto.Page;
import com.xsg.ttshop.common.dto.Result;
import com.xsg.ttshop.pojo.po.TbItem;
import com.xsg.ttshop.pojo.vo.TbItemCustom;
import com.xsg.ttshop.pojo.vo.TbItemQuery;
import com.xsg.ttshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: DHC
 * Date: 2017/11/17
 * Time: 15:19
 * Version:V1.0
 */
@Controller
public class ItemAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemService itemService;
    // ItemService itemService = new ItemServiceImpl();

    @ResponseBody
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    public TbItem printJsonById(@PathVariable("itemId") Long itemId) {
        return itemService.getById(itemId);
    }

    //    @ResponseBody
//    @RequestMapping(value = "/items", method = RequestMethod.GET)
//    public List<TbItem> listItems() {
//        List<TbItem> tbItemList = null;
//        try {
//            tbItemList = itemService.listItems();
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            e.printStackTrace();
//        }
//        return tbItemList;
//    }
    @ResponseBody
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public Result<TbItemCustom> listItems(Page page, Order order, TbItemQuery query) {
        Result<TbItemCustom> result = null;
        try {
            result = itemService.listItems(page,order,query);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/items/batch/{code}", method = RequestMethod.POST)
    public int itemsBatch(@PathVariable("code") byte code,@RequestParam("ids[]") List<Long> ids) {
        int i = 0;
        try {
            i = itemService.itemsBatch(ids,code);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return i;
    }
    @ResponseBody
    @RequestMapping(value = "/item",method = RequestMethod.POST)
    public int saveItem(TbItem tbItem,String content) {
        int i = 0;
        try {
            i = itemService.saveItem(tbItem,content);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return i;
    }


}
