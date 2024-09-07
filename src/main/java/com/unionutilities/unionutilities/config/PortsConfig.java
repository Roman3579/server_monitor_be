package com.unionutilities.unionutilities.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("config.ports")
public class PortsConfig {
    private String lowerBound;
    private String upperBound;
}
