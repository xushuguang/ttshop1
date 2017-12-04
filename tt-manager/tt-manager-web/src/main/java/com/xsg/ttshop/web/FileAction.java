package com.xsg.ttshop.web;

import com.xsg.ttshop.service.impl.FileServiceImpl;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * User: 57140
 * Date: 2017/12/2
 * Time: 13:36
 * Version:V1.0
 */
@Controller
public class FileAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FileServiceImpl fileService;
    @ResponseBody
    @RequestMapping(value = "/file/upload",method = RequestMethod.GET)
    public void config(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //设置请求和响应的字符集和内容类型
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            //获取请求中参数
            String action = request.getParameter("action");
            if (StringUtils.isNotBlank(action)&& "config".equals(action)){
                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.json");
                PrintWriter out = response.getWriter();
                IOUtils.copy(inputStream,out,"UTF-8");
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }

    }
    @ResponseBody
    @RequestMapping(value = "/file/upload",method = RequestMethod.POST)
    public Map<String,Object> upload(MultipartFile upfile){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
             map = fileService.uploadImages(upfile);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return map;
    }
}
