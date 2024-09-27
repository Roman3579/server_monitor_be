package com.unionutilities.unionutilities.config.storage;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("config.files")
public class FileConfig {
    private String resultFilePath;
    private String settingsFilePath;
}
