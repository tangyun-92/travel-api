package com.tang.travel.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 常量值
 */
@Component
public class Constant {

    // 图片上传
    public static String FILE_UPLOAD_FIR;
    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir) {
        FILE_UPLOAD_FIR = fileUploadDir;
    }

}
