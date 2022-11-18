package com.xtxk.core.util.file;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author Administrator
 */
public class FileUtils {

    /***
     * 创建文件夹
     * @param path
     * @return
     * @throws IOException
     */
    public static File mkdirs(String path) throws IOException {
        File desc = new File(path);
        if (!desc.exists()) {
            if (!desc.getParentFile().exists()) {
                desc.getParentFile().mkdirs();
            }
        }
        return desc;
    }


    /***
     * 下载文件
     * @param filePath
     * @return
     */
    public static ResponseEntity<FileSystemResource> downLoad(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache,no-store,must-revalidate");
            headers.add("Content-Disposition", "attachment;filename=" + file.getName());
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Last-Modified", new Date().toString());
            headers.add("ETag", String.valueOf(System.currentTimeMillis()));
            return ResponseEntity.ok().headers(headers).contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/octet-stream")).body(new FileSystemResource(file));
        }
        return null;
    }
}
