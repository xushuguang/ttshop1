package com.xsg.ttshop.portal.web;

import com.xsg.ttshop.common.utils.PropKit;
import com.xsg.ttshop.pojo.po.TbContent;
import com.xsg.ttshop.service.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * User: 57140
 * Date: 2017/12/4
 * Time: 19:17
 * Version:V1.0
 */
@Controller
public class ContentAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ContentService contentService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public  String listContentByCid(Model model){
        try {
            // 查询出结果
            Long cid = PropKit.use("ftp.properties").getLong("ftp.lunboId");
            //放入到model中
            List<TbContent> ad1List = contentService.listContentByCid(cid);
            model.addAttribute("ad1List",ad1List);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return "index";
    }
}
