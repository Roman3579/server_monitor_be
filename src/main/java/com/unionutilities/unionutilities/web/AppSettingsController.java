package com.unionutilities.unionutilities.web;

import com.unionutilities.unionutilities.service.files.AppSettingService;
import com.unionutilities.unionutilities.web.requests.AddTargetRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

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

    @GetMapping("/target")
    public ResponseEntity<Set<String>> getTargets() {
        return ResponseEntity.ok().body(appSettingService.getTargets());
    }

}