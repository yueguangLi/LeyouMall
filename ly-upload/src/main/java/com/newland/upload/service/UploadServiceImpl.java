package com.newland.upload.service;

import com.newland.common.enums.ExceptionEnums;
import com.newland.common.exception.LyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Service

public class UploadServiceImpl {

    private static final List<String> allowTypes = Arrays.asList("image/jpeg","image/png","image/bmp");

    private static Logger log = LoggerFactory.getLogger(UploadServiceImpl.class);
    /**
     * 文件上传
     * @param file
     * @return
     */
    public String uploadService(MultipartFile file){
        // 校验文件类型
        String contentType = file.getContentType();
        if(!allowTypes.contains(contentType)){
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
        File dest = new File("D:/"+file.getOriginalFilename());
        // 保存文件到本地
        try {
            file.transferTo(dest);
            return dest.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            //上传失败
            log.error("上传文件失败",e);
            throw new LyException(ExceptionEnums.UPLOAD_FILE_EXCEPTION);
        }
        // 返回路径
    }
}
