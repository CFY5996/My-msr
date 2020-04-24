package com.msr.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传至阿里云
 * @param file 图片 音频 视频
 * @return文件上传到oss中的路径
 */
public interface FileService {
    String upload(MultipartFile file);
}
