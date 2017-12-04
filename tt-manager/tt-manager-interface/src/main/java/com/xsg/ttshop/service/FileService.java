package com.xsg.ttshop.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * User: 57140
 * Date: 2017/12/2
 * Time: 14:35
 * Version:V1.0
 */
public interface FileService {
    Map<String,Object> uploadImages(MultipartFile myfile);
}
