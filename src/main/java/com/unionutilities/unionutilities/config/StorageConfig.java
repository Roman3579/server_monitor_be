package com.unionutilities.unionutilities.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("config.storage")
public class StorageConfig implements InitializingBean {
    private String folderPath;
    private String resultFilePath;

    @Override
    public void afterPropertiesSet() {
        if (!isPropertySet(this.folderPath)){
            throw new IllegalArgumentException("Storage folder path (config.storage.folder-path) not set.");
        }
        if (!isPropertySet(this.resultFilePath)){
            throw new IllegalArgumentException("Result file name/path (config.storage.result-file-path) not set");
        }
    }

    private Boolean isPropertySet(String propertyValue){
        return propertyValue != null && !propertyValue.trim().isEmpty();
    }
}
