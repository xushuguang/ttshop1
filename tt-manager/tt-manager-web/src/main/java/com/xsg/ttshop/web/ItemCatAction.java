package com.xsg.ttshop.web;

import com.xsg.ttshop.pojo.vo.TreeNode;
import com.xsg.ttshop.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: 57140
 * Date: 2017/11/23
 * Time: 15:15
 * Version:V1.0
 */
@Controller
@Scope("prototype")
public class ItemCatAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ItemCatService itemCatService;

   @ResponseBody
    @RequestMapping(value = "/itemCats/{parentId}",method = RequestMethod.POST)
    public List<TreeNode> listItemCats(@PathVariable("parentId") Long parentId){
       List<TreeNode> list = null;
       try {
           list = itemCatService.listItemCats(parentId);
       }catch (Exception e){
           logger.error(e.getMessage(),e);
           e.printStackTrace();
       }
        return  list;
    }
}
