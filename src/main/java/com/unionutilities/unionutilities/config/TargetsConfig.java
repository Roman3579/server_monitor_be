package com.unionutilities.unionutilities.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties("config")
public class TargetsConfig {
    private List<String> targets;
}
