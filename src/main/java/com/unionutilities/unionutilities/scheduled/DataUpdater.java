package com.unionutilities.unionutilities.scheduled;

import com.unionutilities.unionutilities.service.AppInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@EnableScheduling
@Slf4j
public class DataUpdater {

    private final AppInfoService appInfoService;

    @Scheduled(cron = "${scheduled.data-update.cron}")
    public void updateData(){
        log.info("Running cron to update app info");
        appInfoService.refreshAppInfos();
    }

}
