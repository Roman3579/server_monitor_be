package com.unionutilities.unionutilities.web;

import com.unionutilities.unionutilities.model.ApiCallResultContainer;
import com.unionutilities.unionutilities.model.AppInfoModel;
import com.unionutilities.unionutilities.service.AppInfoService;
import com.unionutilities.unionutilities.service.files.ResultsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/results")
@AllArgsConstructor
@Slf4j
public class AppInfoCollectionController {

    private final AppInfoService appInfoService;
    private final ResultsService resultsService;

    @GetMapping
    public ResponseEntity<ApiCallResultContainer> getAllApiCallResults() throws IOException {
        return ResponseEntity.ok().body(resultsService.getStoredResults());
    }

    @GetMapping("/refresh")
    public ResponseEntity<ApiCallResultContainer> refreshAllApiCallResults() {
        appInfoService.refreshAppInfos();
        Optional<ApiCallResultContainer> containerOptional = appInfoService.getStoredInfos();
        if(containerOptional.isPresent()){
            return ResponseEntity.ok().body(containerOptional.get());
        } else {
            throw new NoSuchElementException();
        }
    }

    @GetMapping("/logs")
    public Resource getLogFile(@RequestParam String url) {
        return appInfoService.getLogs(url);
    }

    @PutMapping
    public ResponseEntity<Boolean> updateAppInfo(@RequestParam String targetUrl, @RequestBody AppInfoModel appInfoModel){
        return ResponseEntity.ok().body(appInfoService.updateAppInfo(targetUrl, appInfoModel));
    }
}
