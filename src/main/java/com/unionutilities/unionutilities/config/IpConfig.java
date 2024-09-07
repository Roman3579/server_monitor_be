package com.unionutilities.unionutilities.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class IpConfig {

    @Value("#{'${config.ip-addresses}'.split(',')}")
    private List<String> ipAddresses;

}
