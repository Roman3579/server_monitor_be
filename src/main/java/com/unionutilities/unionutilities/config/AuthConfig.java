package com.unionutilities.unionutilities.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("auth.technical")
public class AuthConfig {
    private String username;
    private String password;
}
