package com.baidu.ueditor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by RenJie on 2017/7/14 0014.
 */
public class ResourcesUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesUtils.class);

    public static String readFileToString(String path) {
        try {
            if (path.startsWith("classpath:")) {
                path = path.substring(10);
                Resource resource = new ClassPathResource(path);
                return FileCopyUtils.copyToString(new InputStreamReader(resource.getInputStream()));
            } else {
                return FileCopyUtils.copyToString(new FileReader(path));
            }
        } catch (IOException e) {
            LOGGER.warn("file not found in path : {}", path);
            return null;
        }
    }
}
