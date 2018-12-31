package com.newland.upload.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.newland.common.enums.ExceptionEnums;
import com.newland.common.exception.LyException;
import com.newland.upload.config.UploadProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Service
@EnableConfigurationProperties(UploadProperties.class)
public class UploadServiceImpl {

    @Autowired
    private FastFileStorageClient storageClient;
    // private static final List<String> allowTypes = Arrays.asList("image/jpeg","image/png","image/bmp");

    @Autowired
    private UploadProperties prop;
    private static Logger log = LoggerFactory.getLogger(UploadServiceImpl.class);
    /**
     * 文件上传
     * @param file
     * @return
     */
    public String uploadService(MultipartFile file){
        // 校验文件类型
        String contentType = file.getContentType();
        if(!prop.getAllowType().contains(contentType)){
            throw new LyException(ExceptionEnums.INVALID_FILE_TYPE);
        }
        // 校验文件内容
        BufferedImage read = null;
        try {
            read = ImageIO.read(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(read ==null){
            throw new LyException(ExceptionEnums.INVALID_FILE_TYPE);
        }


        // 准备目标路径
        String pathName;
        // File dest = new File("D:/"+file.getOriginalFilename());
        try {
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(),".");
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);
            // 保存文件到本地
            return prop.getBaseUrl()+storePath.getFullPath();
        } catch (Exception e) {
            e.printStackTrace();
            //上传失败
            log.error("[文件上传]上传文件失败",e);
            throw new LyException(ExceptionEnums.UPLOAD_FILE_EXCEPTION);
        }
        // 返回路径
    }
}
