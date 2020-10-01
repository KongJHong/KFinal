package com.hong.common.utils;


import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author : KongJHong
 * @Date : 2020-10-01 18:56
 * @Version : 1.0
 * Description     :
 */
@Component
public class FileUtil {

    private final ApplicationContext applicationContext;

    public FileUtil(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 读取resources下的文件
     * @param path 路径
     * @return 文件内容
     */
    public String readResourceFile(String path) {
        if (!path.startsWith("classpath:")) {
            path = "classpath:" + path;
        }
        Resource resource = applicationContext.getResource(path);
        try {
            StringBuilder sb = new StringBuilder();
            InputStream is = resource.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader bfReader = new BufferedReader(reader);
            String content = null;
            while((content = bfReader.readLine()) != null) {
                sb.append(content);
            }

            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
