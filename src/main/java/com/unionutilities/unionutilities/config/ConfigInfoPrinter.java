package com.unionutilities.unionutilities.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfigInfoPrinter implements ApplicationRunner {

    private TargetsConfig targetsConfig;

    @Override
    public void run(ApplicationArguments args) {
        printConfigInfo();
    }

    private void printConfigInfo() {
        System.out.println("----------------------");
        System.out.println("| Configuration info |");
        System.out.println("----------------------");
        System.out.println("TARGETS");
        for (String target : targetsConfig.getTargets()){
            System.out.println(target);
        }
        System.out.println("----------------------");
    }
}
