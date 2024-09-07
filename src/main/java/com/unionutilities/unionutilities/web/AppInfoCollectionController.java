package com.unionutilities.unionutilities.web;

import com.unionutilities.unionutilities.model.ApiCallResultContainer;
import com.unionutilities.unionutilities.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/results")
@AllArgsConstructor
public class AppInfoCollectionController {

    private final FileService fileService;

    @GetMapping
    public ResponseEntity<ApiCallResultContainer> getAllApiCallResults() throws IOException {
        return ResponseEntity.ok().body(fileService.getStoredResults());
    }

}
