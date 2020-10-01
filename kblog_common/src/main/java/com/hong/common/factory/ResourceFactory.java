package com.hong.common.factory;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Properties;

/**
 * @Author : KongJHong
 * @Date : 2020-10-01 12:34
 * @Version : 1.0
 * Description     : 让PropertySource读取yml文件
 */
public class ResourceFactory extends DefaultPropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(@Nullable String name, EncodedResource resource) throws IOException {
        String sourceName = (name == null) ? resource.getResource().getFilename() : name;
        assert sourceName != null;
        if (sourceName.endsWith(".yml") || sourceName.endsWith(".yaml")) {
            YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
            factory.setResources(resource.getResource());
            factory.afterPropertiesSet();
            Properties properties = factory.getObject();
            assert properties != null;
            return new PropertiesPropertySource(sourceName, properties);
        }
        return super.createPropertySource(name, resource);
    }
}
