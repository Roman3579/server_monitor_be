package com.unionutilities.unionutilities.web;

import com.unionutilities.unionutilities.service.files.AppSettingService;
import com.unionutilities.unionutilities.web.requests.AddTargetRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("api/v1/settings")
@AllArgsConstructor
public class AppSettingsController {

    private final AppSettingService appSettingService;

    @PostMapping("/target")
    public ResponseEntity<?> addTarget(@RequestBody AddTargetRequest addTargetRequest) {
        try {
            appSettingService.addTarget(addTargetRequest);
            return ResponseEntity.ok().body(true);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(false);
        }
    }

}