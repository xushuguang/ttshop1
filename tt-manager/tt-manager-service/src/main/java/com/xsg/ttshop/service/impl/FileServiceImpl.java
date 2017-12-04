package com.xsg.ttshop.service.impl;

import com.xsg.ttshop.common.utils.FtpUtils;
import com.xsg.ttshop.common.utils.IDUtils;
import com.xsg.ttshop.common.utils.Prop;
import com.xsg.ttshop.common.utils.PropKit;
import com.xsg.ttshop.service.FileService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * User: 57140
 * Date: 2017/12/2
 * Time: 14:37
 * Version:V1.0
 */
@Service
public class FileServiceImpl implements FileService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public Map<String, Object> uploadImages(MultipartFile upfile) {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            //即将放置到上传配置文件中的信息upload.properties
            Prop prop = PropKit.use("ftp.properties");
            String host = prop.get("ftp.host");
            Integer port = prop.getInt("ftp.port");
            String username = prop.get("ftp.username");
            String password = prop.get("ftp.password");
            String basePath = prop.get("ftp.basepath");
            String filePath = new DateTime().toString("/yyyy/MM/dd");
            //获取原来的文件名，包括扩展名
            String originalFilename = upfile.getOriginalFilename();
            //截取出扩展名
            String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
            //使用自定义工具类产生新的文件名，只产生了文件名，未产生扩展名
            String newName = IDUtils.genImageName();
            //拼接处新的文件名+扩展名
            newName += fileType;
            InputStream inputStream = upfile.getInputStream();
            //上传成功返回true，否则返回false
            boolean bool = FtpUtils.uploadFile(host, port, username, password, basePath, filePath, newName, inputStream);
            if (bool){
                map.clear();
                map.put("state","SUCCESS");//注意大小写
                map.put("original",originalFilename);
                map.put("size",upfile.getSize());
                map.put("title",newName);
                map.put("type",fileType);
                map.put("url",filePath+"/"+newName);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return map;
    }
}
