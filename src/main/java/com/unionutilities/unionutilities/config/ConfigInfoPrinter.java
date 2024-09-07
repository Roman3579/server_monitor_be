package com.unionutilities.unionutilities.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfigInfoPrinter implements ApplicationRunner {

    private PortsConfig portsConfig;
    private EndpointsConfig endpointsConfig;
    private IpConfig ipConfig;

    @Override
    public void run(ApplicationArguments args) {
        printConfigInfo();
    }

    private void printConfigInfo() {
        System.out.println("----------------------");
        System.out.println("| Configuration info |");
        System.out.println("----------------------");
        System.out.println("IP ADDRESSES");
        ipConfig.getIpAddresses().forEach(System.out::println);
        System.out.println("----------------------");
        System.out.println("PORTS");
        System.out.println("Lower bound: " + portsConfig.getLowerBound());
        System.out.println("Upper bound: " + portsConfig.getUpperBound());
        System.out.println("----------------------");
        System.out.println("ENDPOINTS");
        endpointsConfig.getInfoEndpoints().forEach(System.out::println);
        System.out.println("----------------------");
    }
}
