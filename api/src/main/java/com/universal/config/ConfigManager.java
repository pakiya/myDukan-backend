package com.universal.config;

import com.universal.db.dao.ConfigDao;
import com.universal.entity.http.config.ConfigEntity;
import com.universal.utils.Util;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
@Lazy
public class ConfigManager implements InitializingBean {

    @Autowired
    private Environment env;

    @Autowired(required = true)
    private ConfigDao configDao;

    private Properties prop = new Properties();

    @Override
    public void afterPropertiesSet() throws Exception {
        Properties properties = new Properties();
        for (Iterator<PropertySource<?>> it = ((AbstractEnvironment) env).getPropertySources().iterator(); it.hasNext(); ) {
            PropertySource<?> propertySource = it.next();

            if (propertySource instanceof PropertiesPropertySource) {
                for (String propertyName: ((PropertiesPropertySource) propertySource).getPropertyNames()){
                    properties.put(propertyName, propertySource.getProperty(propertyName));
                }
            }
        }

        /**
         * Fetch config  info from db
         */
        List<ConfigEntity> configEntityList = configDao.readConfigMap();
        Map<String, Object> dbConfigMap = generateConfigMap(configEntityList);
        if (dbConfigMap != null)
            properties.putAll(dbConfigMap);
        this.prop = properties;
    }

    public String getConfig(String key) {
        return getConfig(key, null);
    }

    public String getConfig(String key, String defaultValue) {
        return prop.getProperty(key, defaultValue);
    }

    private Map<String, Object> generateConfigMap(List<ConfigEntity> configEntityList) {
        if (Util.isCollectionNullOrEmpty(configEntityList))
            return null;

        Map<String, Object> configMap = new HashMap<>();
        for (ConfigEntity configEntity : configEntityList) {
            configMap.put(configEntity.getPropertyName(), configEntity.getValue());
        }
        return configMap;
    }

}
