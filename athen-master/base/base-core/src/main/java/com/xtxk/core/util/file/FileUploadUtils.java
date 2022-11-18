package com.xtxk.core.util.file;

import com.xtxk.core.date.DateUtil;
import com.xtxk.core.util.LogUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 文件上传工具类
 *
 * @author Administrator
 */
public class FileUploadUtils {

    /***
     * 返回文件路径
     * @param rootPath 文件根路径
     * @param file
     * @return
     */
    public static String upload(String rootPath,MultipartFile file) {
        String fileName = generateFileName(file);
        File desc = null;
        try {
            desc = getAbsoluteFile(rootPath + "/upload",fileName);
            file.transferTo(desc);
        } catch (IOException e) {
            LogUtil.LOG.error(e.getMessage());
        }
        return  desc.getPath();
    }

    /***
     * 生成文件名
     * @param file
     * @return
     */
    private static String generateFileName(MultipartFile file){
        return DateUtil.today() + File.separator +  LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")) + "." + getExtension(file);
    }

    /***
     * 创建文件路径
     * @param uploadDir
     * @param fileName
     * @return
     * @throws IOException
     */
    private static File getAbsoluteFile(String uploadDir, String fileName) throws IOException {
        File desc = new File(uploadDir + File.separator + fileName);
        if (!desc.exists()) {
            if (!desc.getParentFile().exists()) {
                desc.getParentFile().mkdirs();
            }
        }
        return desc;
    }


    /**
     * 获取文件名的后缀
     *
     * @param file 表单文件
     * @return 后缀名
     */
    public static final String getExtension(MultipartFile file)
    {
        return FilenameUtils.getExtension(file.getOriginalFilename());
    }
}
