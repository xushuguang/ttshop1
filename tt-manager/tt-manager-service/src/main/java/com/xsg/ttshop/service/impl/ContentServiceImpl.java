package com.xsg.ttshop.service.impl;

import com.xsg.ttshop.common.jedis.JedisClient;
import com.xsg.ttshop.common.utils.JsonUtils;
import com.xsg.ttshop.common.utils.StrKit;
import com.xsg.ttshop.dao.TbContentMapper;
import com.xsg.ttshop.pojo.po.TbContent;
import com.xsg.ttshop.pojo.po.TbContentExample;
import com.xsg.ttshop.service.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: 57140
 * Date: 2017/12/4
 * Time: 19:31
 * Version:V1.0
 */
@Service
public class ContentServiceImpl implements ContentService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbContentMapper contentDao;
    @Autowired
    private JedisClient jedisClient;
    @Override
    public List<TbContent> listContentByCid(Long cid) {
        List<TbContent> contentList =null;
        //先判断缓存，如果缓存中有数据直接返回
        try {
            String json = jedisClient.hget("CONTENT_LIST",cid+"");
            if (StrKit.notBlank(json)){
                Integer.parseInt("a");
                contentList = JsonUtils.jsonToList(json,TbContent.class);
                return  contentList;
            }
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        //主业务：去数据库查询(如果没有的话，去数据库查询)
        //创建模板查询
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        contentList = contentDao.selectByExampleWithBLOBs(example);
        //从数据库查到以后添加到缓存的服务器中
        try {
            //存入缓存的hash中
            jedisClient.hset("CONTENT_LIST",cid+"",JsonUtils.objectToJson(contentList));
            return contentList;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return null;
    }
}
